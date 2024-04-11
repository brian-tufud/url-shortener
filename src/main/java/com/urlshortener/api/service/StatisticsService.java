package com.urlshortener.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.api.utils.UtilsService;

@Service
public class StatisticsService {

    @Autowired
    private UtilsService utilsService;

    public void incrementAccessCount(String shortURL) {
        // Increment the access count for the given short URL
    }

    public int getAccessCount(String shortURL) {
        // Get the access count for the given short URL
        return 0;
    }

    public void incrementCreationCount() {
        // Increment the creation count
    }

    public int getCreationCount() {
        // Get the creation count
        return 0;
    }

    public void incrementRedirectionCount() {
        // Increment the redirection count
    }

    public int getRedirectionCount() {
        // Get the redirection count
        return 0;
    }

    public void incrementErrorCount() {
        // Increment the error count
    }

    public int getErrorCount() {
        // Get the error count
        return 0;
    }

    public void incrementNotFoundCount() {
        // Increment the not found count
    }

    public int getNotFoundCount() {
        // Get the not found count
        return 0;
    }

}
