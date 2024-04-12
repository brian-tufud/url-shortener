package com.urlshortener.api.utils;

public class Constants {
    public static final String SHORT_URL_PREFIX = "https://7f52-2800-a4-22a5-c200-3480-7248-813b-860e.ngrok-free.app/";

    public static final String IP_STACK_URL = "http://api.ipstack.com/";

    //TODO - borrar esto de acá y ponerlo como env variable en la instancia al momento del deploy
    public static final String IP_STACK_ACCESS_KEY = "f6ae78026243789416ef752878bf75c9";

    public static final String STATISTICS_QUEUE = "statistics_queue";

    public static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"
    };
}
