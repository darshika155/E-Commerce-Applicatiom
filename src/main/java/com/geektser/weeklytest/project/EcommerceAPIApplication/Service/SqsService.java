package com.geektser.weeklytest.project.EcommerceAPIApplication.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.UUID;

@Service
public class SqsService {
    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String messageBody) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .messageGroupId("my-group-id")
                .messageDeduplicationId(UUID.randomUUID().toString())
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}
