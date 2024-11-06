package com.geektser.weeklytest.project.EcommerceAPIApplication.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SqsConfig {
    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder().build();
    }
}
