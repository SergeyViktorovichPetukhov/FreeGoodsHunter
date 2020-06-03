package com.sergo.wic.google_api;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class SimilarWebChecker extends AbstractWebsiteChecker {

    private static final String API_KEY = "dcwrf3e4f344";

    public static Map<String, String> prepareValues(String APIkey, String url, String startDate, String endDate, Locale locale){
        Map<String, String> preparedValues = new HashMap<>();
        preparedValues.put("api_key",APIkey);
        String preparedWebsiteName = null;
        try {
            preparedWebsiteName = new URL(url).getHost();
            System.out.println(preparedWebsiteName);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException("malformed exception");
        }
        preparedValues.put("website",preparedWebsiteName);
        try {
            SimpleDateFormat startDateFormat = new SimpleDateFormat("YYYY-MM");
            Date start = startDateFormat.parse(startDate);
            String startDate1 = startDateFormat.format(start);
            System.out.println(startDate1 + " start date");
            preparedValues.put("start_date",startDateFormat.format(start));
            SimpleDateFormat endDateFormat = new SimpleDateFormat("YYYY-MM");
            Date end = endDateFormat.parse(endDate);
            System.out.println(end);
            preparedValues.put("end_date",startDateFormat.format(end));
        }catch (ParseException e){
            e.printStackTrace();
            throw new RuntimeException("parse exception");
        }
        CountryCode cc = CountryCode.getByLocale(locale);
        preparedValues.put("country_code",cc.getAlpha2());
        System.out.println(cc.getAlpha2());
        return preparedValues;
    }

    @Override
    public boolean APIcall(Map<String, String> values) {

        String url = "https://api.similarweb.com/v1/website/" + values.get("website")
                + "/total-traffic-and-engagement/visits?api_key=" + values.get("api_key")
                + "&start_date=" + values.get("start_date")
                + "&end_date=" + values.get("end_date")
                + "&country=" + values.get("country_code")
                + "&granularity=monthly&main_domain_only=false&format=json";

        return false;
    }

    @Override
    protected boolean check() {
        return false;
    }
}
