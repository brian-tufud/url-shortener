package com.read.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.read.api.service.StatisticsService;
import com.read.api.service.UrlService;
import com.read.api.utils.UtilsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;
    private final StatisticsService statisticsService;
    private final UtilsService utilsService;

    public UrlController(UrlService urlService, StatisticsService statisticsService, UtilsService utilsService) {
        super();
        this.urlService = urlService;
        this.statisticsService = statisticsService;
        this.utilsService = utilsService;
    }

    @GetMapping("/{short_url}") 
    public ResponseEntity<Void> getLongURL(HttpServletRequest request,
        @PathVariable(value = "short_url") String shortURL) throws Exception {

        String longURL = urlService.getLongURL(shortURL);
        
        statisticsService.sendDataForStatistics(request, shortURL);

        return utilsService.redirect(longURL);
    }
    
}
