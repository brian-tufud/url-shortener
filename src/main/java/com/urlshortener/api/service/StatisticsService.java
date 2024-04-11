package com.urlshortener.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.api.utils.UtilsService;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

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

    public void getUserAgentData(HttpServletRequest request) {
        String userAgentString = request.getHeader("User-Agent");

        UserAgentAnalyzer uaa = UserAgentAnalyzer
                    .newBuilder()
                    .hideMatcherLoadStats()
                    .withoutCache()
                    .build();

        UserAgent agent = uaa.parse(userAgentString);

        String deviceClass = agent.getValue("DeviceClass");
        String deviceName = agent.getValue("DeviceName");
        String deviceBrand = agent.getValue("DeviceBrand");
        String operatingSystemName = agent.getValue("OperatingSystemName");
        String operatingSystemVersion = agent.getValue("OperatingSystemVersion");
        String agentName = agent.getValue("AgentName");
        String agentVersion = agent.getValue("AgentVersion");

        return;
        
    }

}
