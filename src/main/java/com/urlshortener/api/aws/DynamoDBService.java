package com.urlshortener.api.aws;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import com.urlshortener.api.utils.Constants;

import org.springframework.stereotype.Service;

@Service
public class DynamoDBService {

    private final DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
            .region(Region.US_EAST_1)
            .build();
    
    @PreDestroy
    public void cleanUp() {
        dynamoDbClient.close();
    }

}
