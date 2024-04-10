package com.urlshortener.api.controller;

import com.urlshortener.api.service.UrlService;
import com.urlshortener.api.utils.utilsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UrlController {

    private final UrlService urlService;
    private final utilsService utilsService;

    public UrlController(UrlService urlService, utilsService utilsService) {
        super();
        this.urlService = urlService;
        this.utilsService = utilsService;
    }
    
}
