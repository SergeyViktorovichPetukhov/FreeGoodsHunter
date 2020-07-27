package com.sergo.wic.company_check;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AWSAPIChecker {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    WebClient.Builder webClientBuilder;

    private static final String API_KEY = "YlG8cHVP8w86pu1VqgXM024YM4MAoiy75BfXpCWq";
    private static final String URL_INFO = "https://awis.api.alexa.com/api?Action=urlInfo&ResponseGroup=Rank&Output=json&Url=";
    private static final String TRAFFIC_HISTORY_BEGIN = "https://awis.api.alexa.com/api?Action=TrafficHistory&ResponseGroup=History";
    private static final String TRAFFIC_HISTORY_MIDDLE = "&TrafficHistory.%d.Start=%s";
    private static final String TRAFFIC_HISTORY_END = "&Output=json&Url=";
    private static final String CATEGORY_BROWSE = "https://awis.api.alexa.com/api?Action=CategoryBrowse&ResponseGroup=Categories,RelatedCategories,LanguageCategories&Descriptions=True&Path=Top/Business/Automotive&Output=json";
    private static final double MIN_VIEWS_PER_MILLION = 100.00;
    private static final double MIN_VIEWS_PER_USER = 1.20;

    public void getAlexaTrafficHistory(String domainName){


            JSONObject object = getApiResponse(getHistoryTrafficUri(domainName));
//            BufferedReader br = new BufferedReader(new FileReader("src/batch response.txt"));
//            JSONObject object = new JSONObject(br.lines().collect(Collectors.joining("\n")));
//            JSONObject obj2 = object.getJSONObject("Awis")
//                    .getJSONObject("Results")
//                    .getJSONObject("Result")
//                    .getJSONObject("Alexa")
//                    .getJSONObject("TrafficHistory")
//                    .getJSONObject("HistoricalData");
//            JSONArray array = obj2.getJSONArray("Data");

            JSONArray result = object.getJSONObject("Awis")
                      .getJSONObject("Results")
                      .getJSONArray("Result");

            int countPermMillion = 0;
            int countPerUser = 0;
            JSONArray data = null;
            for (int i = 0; i < result.length(); i++) {
                try {
                    System.out.println(result.getJSONObject(i).getJSONObject("Alexa").getJSONObject("TrafficHistory").get("Start"));
                    JSONObject historicalData = result.getJSONObject(i)
                            .getJSONObject("Alexa")
                            .getJSONObject("TrafficHistory")
                            .getJSONObject("HistoricalData");
                    data = historicalData.getJSONArray("Data");
                }catch (JSONException e){
                    if (e.getMessage().equals("JSONObject[\"HistoricalData\"] is not a JSONObject.")){
                        System.out.println("no data");
                        countPermMillion++;
                        countPerUser++;
                        continue;
                    }
                }
                if (data == null){
                    System.out.println("data == null");
                    continue;
                }

                double viewsPerMillion = 0.00;
                double viewsPerUser = 0.00;
                for (int j = 0; j < data.length(); j++) {
                    viewsPerMillion += Double.valueOf((String) data
                            .getJSONObject(j)
                            .getJSONObject("PageViews")
                            .get("PerMillion"));
                    viewsPerUser += Double.valueOf((String) data
                            .getJSONObject(j)
                            .getJSONObject("PageViews")
                            .get("PerUser"));
                }
                viewsPerUser /= data.length();
                System.out.println(viewsPerMillion + " views per million at one month");
                System.out.println(viewsPerUser + " views per user at one month");
                if (viewsPerMillion < MIN_VIEWS_PER_MILLION)
                    countPermMillion ++;
                if (viewsPerUser < MIN_VIEWS_PER_USER)
                    countPerUser ++;
                System.out.println("count = " + countPermMillion);
                if (countPermMillion > 1)
                    System.out.println("count per million > 1");
                if (countPerUser > 1)
                    System.out.println("count per user > 1");
            }
    }

    public void getAlexaCategories(){
//        JSONObject object = new JSONObject(getApiResponse(CATEGORY_BROWSE));
        BufferedReader br = null;
        try {
           br = new BufferedReader(new FileReader("src/batch response.txt"));
         }catch (FileNotFoundException e){e.printStackTrace();}
            JSONObject object = new JSONObject(br.lines().collect(Collectors.joining("\n")));

        JSONArray category = object.getJSONObject("Awis")
                .getJSONObject("Results")
                .getJSONObject("Result")
                .getJSONObject("Alexa")
                .getJSONObject("CategoryBrowse")
                .getJSONObject("Categories")
                .getJSONArray("Category");
        String[] categories = new String[category.length()];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = (String) category.getJSONObject(i).get("Title");
            System.out.println(categories[i]);
        }

    }

    public void getAlexaLinksInCount(String url){
        JSONObject object = getApiResponse(URL_INFO + url);
        String linksInCount = (String) object.getJSONObject("Awis")
                    .getJSONObject("Results")
                    .getJSONObject("Result")
                    .getJSONObject("Alexa")
                    .getJSONObject("ContentData")
                    .get("LinksInCount");
        System.out.println(linksInCount);

    }
    public String getAlexaRank(String url){
        JSONObject object = getApiResponse(URL_INFO + url);
        if (object.getJSONObject("Awis")
                .getJSONObject("Results")
                .getJSONObject("Result")
                .getJSONObject("Alexa")
                .getJSONObject("TrafficData")
                .isNull("Rank")){
            return "0";
        }
        else {
            return String.valueOf(object.getJSONObject("Awis")
                    .getJSONObject("Results")
                    .getJSONObject("Result")
                    .getJSONObject("Alexa")
                    .getJSONObject("TrafficData")
                    .get("Rank"));
        }
    }

    private JSONObject getApiResponse(String uri){
        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create()
                .get()
                .uri(uri)
                .header("x-api-key",API_KEY);
        String response = requestSpec2.exchange()
                .block()
                .bodyToMono(String.class)
                .block();
//        System.out.println(response);
//        System.out.println("response");
        return new JSONObject(response);

    }

    private String getPeriodStartDate(Integer i){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -i);
        return dateFormat.format( calendar.getTime());
    }
    private String getHistoryTrafficUri(String uri){
        StringBuilder sb = new StringBuilder(TRAFFIC_HISTORY_BEGIN);
        for (int i = 5 , j = 1; i > 0 ; i--, j++) {
            sb.append(String.format(TRAFFIC_HISTORY_MIDDLE,j,getPeriodStartDate(i)));
        }
        System.out.println(sb.append(TRAFFIC_HISTORY_END).append(uri).toString());
        return sb.append(TRAFFIC_HISTORY_END).append(uri).toString();
    }
}
