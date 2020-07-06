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

    public boolean checkHtmlPageByPhone(String url, String phone){
        System.out.println(phone);
      //  System.out.println(System.currentTimeMillis());
        long a1 = System.currentTimeMillis();
        Document doc = connect(url);
        if (doc == null)
            return false;
        Elements links = doc.select("a");
        if (links == null)
            return false;
        List<String> text = links.eachText();
        if (text.stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(phone)))
            return true;
        else {
            String href = links.select("a[href*=\"/contacts/\"]").attr("href");
            System.out.println(href);
            if (href != null) {
                doc = connect(href);
            }
            if (doc == null) {
                String href2 = links.select("a[href*=/kontakty]").attr("href");
                if (href2 != null) {
                    doc = connect(url + href2);
                }
            }
            if (doc == null) {
                String href2 = links.select("a[href*=/kontakts]").attr("href");
                if (href2 != null) {
                    doc = connect(url + href2);
                }
            }
            Elements a = doc.select("a");
            Elements p = doc.select("p");
            List<String> text2 = a.eachText();
//            text2.forEach(System.out::println);
//            System.out.println("++++++++++");
            List<String> text3 = p.eachText();
          //  text3.forEach(System.out::println);
            text3.stream().filter(str -> str.matches(Constants.PHONE_REGEX)).collect(Collectors.toList()).forEach(System.out::println);
            if (text2.stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(phone))
             | text3.stream().filter(str -> str.matches(Constants.PHONE_REGEX)).anyMatch((str) -> str.equals(phone)))
                return true;
        }
        long b = System.currentTimeMillis();
//        System.out.println(b - a1 + " delta");
        return false;
    }

    public boolean checkHtmlPageByEmail(String url, String email){

        Document doc = connect(url);
        System.out.println("1");
        if (doc == null)
            return false;
        Elements links = doc.select("a");
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
                String href2 = links.select("a[href*=/kontakty]").attr("href");
                if (!href2.isEmpty()) {
                    doc = connect(url + href2);
                }
            }
            if (doc == null) {
                String href2 = links.select("a[href*=/kontakts]").attr("href");
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
            System.out.println(url + " url ");
            return Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
