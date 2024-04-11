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
        // Definir el alfabeto para la codificación base 62
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder base62 = new StringBuilder();
        // Codificar el número en base 62
        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = num.divideAndRemainder(BigInteger.valueOf(62));
            base62.insert(0, alphabet.charAt(divmod[1].intValue()));
            num = divmod[0];
        }
        return base62.toString();
    }

}
