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

    public void insert(String shortUrl, String longUrl) throws Exception {
        Map<String, AttributeValue> item = buildItem(shortUrl, longUrl);
        String shard = getCorrespondingShard(shortUrl);
        insertRecord(item, shard);
    }

    private Map<String, AttributeValue> buildItem(String shortUrl, String longUrl) {
        Map<String, AttributeValue> item = new HashMap<>();

        item.put("short_url", AttributeValue.builder().s(shortUrl).build());
        item.put("long_url", AttributeValue.builder().s(longUrl).build());
        item.put("created_at", AttributeValue.builder().s(String.valueOf(System.currentTimeMillis())).build());

        return item;
    }

    private String getCorrespondingShard(String shortUrl) {
        char firstChar = shortUrl.charAt(0);
    
        if (firstChar >= '0' && firstChar <= '9') {
            return Constants.DB_SHARD_A;
        } else if (firstChar >= 'a' && firstChar <= 'm') {
            return Constants.DB_SHARD_B;
        } else if (firstChar >= 'n' && firstChar <= 'z') {
            return Constants.DB_SHARD_C;
        } else if (firstChar >= 'A' && firstChar <= 'M') {
            return Constants.DB_SHARD_D;
        } else if (firstChar >= 'N' && firstChar <= 'Z') {
            return Constants.DB_SHARD_E;
        }

        throw new IllegalArgumentException("Invalid short URL");
    }

    private void insertRecord(Map<String, AttributeValue> item, String shard) {
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(shard)
                .item(item)
                .build();
        
        dynamoDbClient.putItem(putItemRequest);
    }
    
    @PreDestroy
    public void cleanUp() {
        dynamoDbClient.close();
    }

}
