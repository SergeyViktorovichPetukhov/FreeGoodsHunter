package com.sergo.wic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WicApplication {

//    @Bean
//    public TypeMap<Share, GetShareResponse> getShareResponseTypeMap(){
//        return new ExpressionMap<Share, GetShareResponse>()
//
//    }
    public static void main(String[] args) {
        SpringApplication.run(WicApplication.class, args);
    }

}
