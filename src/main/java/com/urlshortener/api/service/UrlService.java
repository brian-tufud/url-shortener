package com.urlshortener.api.service;

import com.urlshortener.api.repository.UrlRepositoryService;
import com.urlshortener.api.utils.utilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UrlService {
    
    @Autowired
    private UrlRepositoryService  userRepositoryService;
   
    
}
