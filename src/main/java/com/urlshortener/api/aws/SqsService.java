package com.urlshortener.api.aws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SqsService {

    @Autowired
    private Environment env;

    private final SqsClient sqs = SqsClient.builder()
                .region(Region.US_EAST_1)
                .build();

    public boolean post(String queue, HashMap<String, String> metadata) {

        String queueUrl = env.getProperty(queue);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String json = gson.toJson(metadata);

        SendMessageRequest sendMessageStandardQueue = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(json)
                .delaySeconds(0)
                .messageGroupId(metadata.get("short_url"))
                .build();

        sqs.sendMessage(sendMessageStandardQueue);

        return true;
    }
}
