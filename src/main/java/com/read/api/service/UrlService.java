package com.read.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.read.api.dto.LongURLDto;
import com.read.api.repository.UrlRepository;
import com.read.api.utils.UtilsService;

@Service
public class UrlService {

    @Autowired
    private UtilsService utilsService;
    
    @Autowired
    private UrlRepository urlRepository;

    public LongURLDto getLongURL(String shortURL) {
        String longUrl = urlRepository.getLongUrl(shortURL);

        return utilsService.getLongURLDto(longUrl);
    }

}
