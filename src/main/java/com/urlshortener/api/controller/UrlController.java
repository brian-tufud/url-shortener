package com.urlshortener.api.controller;

import com.urlshortener.api.service.UrlService;
import com.urlshortener.api.utils.UtilsService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UrlController {

    private final UrlService urlService;
    private final UtilsService utilsService;

    public UrlController(UrlService urlService, UtilsService utilsService) {
        super();
        this.urlService = urlService;
        this.utilsService = utilsService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(HttpServletRequest request) throws Exception {

        String cart = urlService.shortenURL(request.getParameter("url"));

        HttpHeaders responseHeaders = utilsService.getResponseHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(cart);
    }

    @GetMapping("/{short_url}") 
    public ResponseEntity<Void> getLongURL(@PathVariable(value = "short_url") String shortURL) throws Exception {

        String longURL = urlService.getLongURL(shortURL);

        return utilsService.redirect(longURL);
    }
    
}
