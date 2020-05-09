package com.sergo.wic;

import com.sergo.wic.entities.Item;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.mapping.Column;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class WicApplication {

//    @Bean
//    public TypeMap<Share, GetShareResponse> getShareResponseTypeMap(){
//        return new ExpressionMap<Share, GetShareResponse>()
//
//    }
    public static void main(String[] args) {

        SpringApplication.run(WicApplication.class, args);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(SessionFactoryImpl.class);
//        applicationContext.refresh();
//        SessionFactoryImpl sessionFactoryBean = (SessionFactoryImpl) applicationContext.getBean("sessionFactory");
//        ClassMetadata classMetadata = sessionFactoryBean.getClassMetadata(Item.class);
//        String[] propertyNames = classMetadata.getPropertyNames();
//        for(String s : propertyNames){
//            System.out.println(s);
//        }
    }


}
