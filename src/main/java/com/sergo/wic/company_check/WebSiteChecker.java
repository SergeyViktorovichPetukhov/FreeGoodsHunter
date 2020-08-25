package com.sergo.wic.company_check;

import com.sergo.wic.utils.Constants;
import com.sergo.wic.utils.EmailValidator;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class WebSiteChecker {
//    private static final String REGEX = "^((d|\\+d)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

//    private static final Pattern PATTERN = Pattern.;
    private StringBuffer baseUrl;

    public boolean checkHtmlPageByPhone(String url, String phone){
        String formattedPhone = phone.replaceAll("[\\s-()]","");
        String URL = "https://" + url;
        System.out.println(formattedPhone);
        Document doc = getContactsPage(URL,formattedPhone);

        if (doc == null){
            System.out.println("doc=null");
            return false;
        }

        return  (doc.select("a").eachText().stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(formattedPhone))
               | doc.select("p").eachText().stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(formattedPhone))
               | doc.select("em").eachText().stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(formattedPhone)));
    }

    public boolean checkHtmlPageByEmail(String URL, String email){
        String url = "https://" + URL;
        baseUrl = new StringBuffer(url);
        Document doc = connect(url);
        System.out.println("1");
        if (doc == null)
            return false;
        Elements links = doc.select("a");
    //    links.eachText().forEach(System.out::println);
        List<String> text = links.eachText();
        if (text.stream().filter(str -> EmailValidator.isValid(str.toCharArray())).anyMatch((str) -> str.equals(email)))
            return true;
        else {
            String href = links.select("a[href*=\"/contacts\"]").attr("href");

            if (!href.isEmpty() & !href.startsWith("http")) {
                System.out.println("2");
                System.out.println(href + " href");
                doc = connect(href);
            }else
                doc = connect(url + href);
            System.out.println("3 " + url + href);
            if (href.isEmpty()) {
                // https://labsintez.com/kontakty/
                String href2 = links.select("a[href*=/kontakt]").attr("href");
                if (!href2.isEmpty()) {
                    doc = connect(url + href2);
                }
            }
            if (doc == null) {
                String href2 = links.select("a[href*=/Контакты]").attr("href");
                if (href2 != null) {
                    doc = connect(url + href2);
                }
            }
            Elements contacts = doc.select("a");
            List<String> text2 = contacts.eachText();
            if (text2.stream().filter(str -> EmailValidator.isValid(str.toCharArray())).anyMatch((str) -> str.equals(email)))
                return true;
        }
        return false;
    }

    private Document connect(String url){
        try {
            System.out.println(url + " current url ");
            String newUrl;
            if (!url.startsWith("https")){
                newUrl = baseUrl.toString() + url;
                return Jsoup.connect(newUrl).get();
            }
            return Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Document getContactsPage(String url, String formattedPhone){
        Document doc;
        doc = connect(url);
        if (doc != null) {
            Elements links = doc.select("a");
            List<String> text = links.eachText();
            if (text.stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(formattedPhone))) {
                System.out.println(1);
                return doc;
            }

            String href = links.select("a[href*=\"/contacts/\"]").attr("href");
            if (href != null) {
                System.out.println(2);
                doc = connect(url + href);
            }
            String href2 = null;
            if (href == null) {
                href2 = links.select("a[href*=/kontakt]").attr("href");
                if (href2 != null) {
                    System.out.println(3);
                    doc = connect(url + href2);
                }
            }
            String href3;
            if (href2 == null) {
                href3 = links.select("a[href*=/Контакты]").attr("href");
                if (href3 != null) {
                    System.out.println(4);
                    doc = connect(url + href3);
                }
            }
        }
        return doc;
    }
}
