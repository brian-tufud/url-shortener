package com.urlshortener.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.urlshortener.api.response.IpStackResponse;
import com.urlshortener.api.utils.Constants;
import com.urlshortener.api.utils.UtilsService;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

@Service
public class StatisticsService {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IpStackService ipStackService;

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

        IpStackResponse ipData = getIpData(request);

        return;
        
    }

    public IpStackResponse getIpData(HttpServletRequest request) {
        String ipAddress = getClientIpAddress(request);

        return getIpDetails(ipAddress);
    }

    private IpStackResponse getIpDetails(String ipAddress) {
        return ipStackService.getIpDetails(ipAddress);
    }

    private String getClientIpAddress(HttpServletRequest request) {

        if (RequestContextHolder.getRequestAttributes() == null) {
            return "0.0.0.0";
        }

        for (String header: Constants.IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

}
