package com.sergo.wic.google_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergo.wic.service.email.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class AWSAPIChecker {

    @Autowired
    ObjectMapper mapper;

    private static final String API_KEY = "2qoqcpUF4Q5jO4UXQrR0O8jfKPWOmvmH6Dug6DWx";
    private static final String API_TEMPLATE = "https://awis.api.alexa.com/api?Action=urlInfo&ResponseGroup=Rank,SiteData&Output=json&Url=";

    public void APICall(String domainName){
        WebClient client = WebClient
                .builder()
                .baseUrl(API_TEMPLATE)
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> request = client.method(HttpMethod.GET);
        WebClient.RequestHeadersSpec<?> requestSpec = WebClient
                .create()
                .get()
                .uri(API_TEMPLATE + domainName)
                .header("x-api-key",API_KEY);
        String response = requestSpec.exchange()
                .block()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
    }
}
