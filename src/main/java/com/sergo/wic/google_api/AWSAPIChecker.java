package com.sergo.wic.google_api;

import com.sergo.wic.service.email.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AWSAPIChecker implements ClientHttpRequestInterceptor {

    final static Logger log = LoggerFactory.getLogger(Test.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders() );
        log.info("Request body: {}", new String(body, "UTF-8"));
        log.info("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.info("============================response begin==========================================");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("Response body: {}", inputStringBuilder.toString());
        log.info("=======================response end=================================================");
    }

    private static final String API_KEY = "2qoqcpUF4Q5jO4UXQrR0O8jfKPWOmvmH6Dug6DWx";
    private static final String API_TEMPLATE = "https://awis.api.alexa.com/api?Action=urlInfo&ResponseGroup=Rank,SiteData&Url=";


    public void APICall(String domainName){

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new AWSAPIChecker());
        String APIurl = API_TEMPLATE + domainName;

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> values = new HashMap<>();
        values.put("Accept", "application/json");
        values.put("x-api-key",API_KEY);
        headers.setAll(values);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(APIurl, String.class, entity);
        }catch (HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
        }

    }
}
