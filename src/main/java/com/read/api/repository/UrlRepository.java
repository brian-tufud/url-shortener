package com.read.api.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.read.api.aws.DynamoDBService;
import com.read.api.exception.NotFoundException;
import com.read.api.utils.Constants;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Service
public class UrlRepository {

    @Autowired
    private DynamoDBService dynamoDBService;

    public String getLongUrl(String shortUrl) {
        String shard = getCorrespondingShard(shortUrl);
        Map<String, AttributeValue> item = dynamoDBService.getItem(shortUrl, shard);

        if (item == null) {
            throw new NotFoundException("Short URL " + shortUrl + " not found");
        }

        return item.get("long_url").s();
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
	
}
