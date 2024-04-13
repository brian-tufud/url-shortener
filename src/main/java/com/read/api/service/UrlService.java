package com.read.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.read.api.repository.UrlRepository;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;

    public String getLongURL(String shortURL) {
        return urlRepository.getLongUrl(shortURL);
    }

}
