package com.sergo.wic.service.sms;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SmsServiceImpl implements SmsService {

    private static final String API_KEY = "4e2ef5025bmsh542a4974ce06215p13b121jsnac5d13b12354";
    private static final String REQUEST_URI = "https://telesign-telesign-send-sms-verification-code-v1.p.rapidapi.com/sms-verification-code";

    @Override
    public void sendVerifyCode(String code, String phone) {
        getApiResponse(code,phone);
    }

    private static JSONObject getApiResponse(String code, String phone){
        BodyInserter<Verification, ReactiveHttpOutputMessage> inserter3
                = BodyInserters.fromObject(new Verification(code,phone));

        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create()
                .method(HttpMethod.POST)
                .uri(REQUEST_URI)
                .body(BodyInserters.fromObject(new Verification(code,phone)))
                .header("x-api-key",API_KEY);

        String response = requestSpec2.exchange()
                .block()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
        System.out.println("response");
        return new JSONObject(response);

    }

    public static void main(String[] args) {
        JSONObject object = getApiResponse("2345324","+79684823223");
        System.out.println(object.toString());
    }
}
