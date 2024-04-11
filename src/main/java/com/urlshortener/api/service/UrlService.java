package com.urlshortener.api.service;

import com.urlshortener.api.repository.UrlRepositoryService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepositoryService userRepositoryService;

    private final String SHORT_URL_PREFIX = "https://me.li/";

    public String shortenURL(String longURL) {
        try {
            String randomSuffix = generateRandomSuffix(6);

            String longURLWithSuffix = longURL + randomSuffix;
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(longURLWithSuffix.getBytes());
            BigInteger hashInt = new BigInteger(1, digest);
            
            String base62Fragment = encodeBase62(hashInt);
            
            String shortFragment = base62Fragment.substring(0, 6);
            
            String shortURL = SHORT_URL_PREFIX + shortFragment;

            return shortURL;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateRandomSuffix(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder suffix = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            suffix.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return suffix.toString();
    }

    private String encodeBase62(BigInteger num) {
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder base62 = new StringBuilder();
        
        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = num.divideAndRemainder(BigInteger.valueOf(62));
            base62.insert(0, alphabet.charAt(divmod[1].intValue()));
            num = divmod[0];
        }
        return base62.toString();
    }

}
