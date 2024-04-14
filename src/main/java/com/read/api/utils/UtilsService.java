package com.read.api.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.read.api.dto.LongURLDto;

@Service
public class UtilsService {

    public HttpHeaders getResponseHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        responseHeaders.set("Access-Control-Allow-Headers",
                "Content-Type, X-AUTH-TOKEN ,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization, Accept,Content-Disposition");
        responseHeaders.set("Access-Control-Expose-Headers",
                "content-type,  X-AUTH-TOKEN, X-AUTH-ROLES, Authorization,Content-Disposition");

        return responseHeaders;
    }

    public LongURLDto getLongURLDto(String longURL) {
        LongURLDto longURLDto = new LongURLDto();
        
        longURLDto.setLongURL(longURL);
    
        return longURLDto;
    }

}
