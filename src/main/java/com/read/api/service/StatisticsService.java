package com.read.api.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.read.api.aws.SqsService;
import com.read.api.utils.Constants;
import com.read.api.utils.EventTypes;

@Service
public class StatisticsService {

    @Autowired
    private SqsService sqsService;


    public void sendDataForStatistics(HttpServletRequest request, String shortURL) {
        HashMap<String, String> metadata = getMetadata(request, shortURL);
        sqsService.post(Constants.STATISTICS_QUEUE, metadata);
    }

    private HashMap<String, String> getMetadata(HttpServletRequest request, String shortURL) {
        HashMap<String, String> metadata = new HashMap<String, String>();

        metadata.put("event_type", EventTypes.URL_ACCESSED);
        metadata.put("short_url", shortURL);
        metadata.put("client_ip", getClientIpAddress(request));
        metadata.put("user_agent", request.getHeader("User-Agent"));
        metadata.put("accessed_at", String.valueOf(System.currentTimeMillis()));

        return metadata;
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
