package com.demo.integration.rest;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MyRouteBuilder extends RouteBuilder {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Value("${spring.kafka.bootstrap}")
    private String bootstrap;

  
    public void configure() throws Exception {

        
        rest("/v1/{id}")
        .put()
        .route()
        .routeId("Kafka-producer-sb")
        .convertBodyTo(String.class)
        .to("kafka:"+topic+"?brokers="+bootstrap)    
        .log("Message confirmation topic! : \"${body}\" ");

    }

}