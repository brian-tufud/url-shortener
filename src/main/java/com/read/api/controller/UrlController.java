package com.read.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.read.api.dto.LongURLDto;
import com.read.api.service.UrlService;
import com.read.api.utils.UtilsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;
    private final UtilsService utilsService;

    public UrlController(UrlService urlService, UtilsService utilsService) {
        super();
        this.urlService = urlService;
        this.utilsService = utilsService;
    }

    @GetMapping("/{short_url}") 
    public ResponseEntity<LongURLDto> getLongURL(HttpServletRequest request,
        @PathVariable(value = "short_url") String shortURL) throws Exception {

        LongURLDto longURL = urlService.getLongURL(shortURL);

        HttpHeaders responseHeaders = utilsService.getResponseHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(longURL);
    }
    
}
