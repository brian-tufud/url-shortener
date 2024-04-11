package com.urlshortener.api.service;

import com.urlshortener.api.repository.UrlRepositoryService;
import com.urlshortener.api.utils.Constants;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepositoryService urlRepositoryService;

    public String getLongURL(String shortURL) {
        return "https://articulo.mercadolibre.com.uy/MLU-460530019-carpa-6-personas-colchon-inflable-de-2-plazas-inflador--_JM#reco_item_pos=2&reco_backend=item_decorator&reco_backend_type=function&reco_client=home_items-decorator-legacy&reco_id=583bf3a0-af13-4478-8184-239917f20781&c_id=/home/navigation-trends-recommendations/element&c_uid=994aa749-bccd-43cd-8c0a-af26a4c77820&da_id=navigation_trend&da_position=2&id_origin=/home/dynamic_access&da_sort_algorithm=ranker";
    }

    public String shortenURL(String longURL) {
        try {
            String randomSuffix = generateRandomSuffix(6);

            String longURLWithSuffix = longURL + randomSuffix;

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(longURLWithSuffix.getBytes());
            BigInteger hashInt = new BigInteger(1, digest);
            
            String base62Fragment = encodeBase62(hashInt);
            
            String shortFragment = base62Fragment.substring(0, 6);
            
            String shortURL = Constants.SHORT_URL_PREFIX + shortFragment;

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
