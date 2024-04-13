package com.urlshortener.api.utils;

public class Constants {
    public static final String SHORT_URL_PREFIX = "https://7f52-2800-a4-22a5-c200-3480-7248-813b-860e.ngrok-free.app/";

    public static final String IP_STACK_URL = "http://api.ipstack.com/";

    //TODO - borrar esto de acá y ponerlo como env variable en la instancia al momento del deploy
    public static final String IP_STACK_ACCESS_KEY = "f6ae78026243789416ef752878bf75c9";

    public static final String STATISTICS_QUEUE = "statistics_queue";

    public static final Integer ZERO_NUMERIC_VALUE = 48;

    public static final Integer NINE_NUMERIC_VALUE = 57;

    public static final Integer LOWERCASE_A_VALUE = 97;

    public static final Integer LOWERCASE_M_VALUE = 109;

    public static final Integer LOWERCASE_N_VALUE = 110;

    public static final Integer LOWERCASE_Z_VALUE = 122;

    public static final Integer UPPERCASE_A_VALUE = 65;

    public static final Integer UPPERCASE_M_VALUE = 77;

    public static final Integer UPPERCASE_N_VALUE = 78;

    public static final Integer UPPERCASE_Z_VALUE = 90;

    private static final String DB_TABLE_PREFIX = "short_url_shard_";
    
    public static final String DB_SHARD_A = DB_TABLE_PREFIX + "a";

    public static final String DB_SHARD_B = DB_TABLE_PREFIX + "b";

    public static final String DB_SHARD_C = DB_TABLE_PREFIX + "c";

    public static final String DB_SHARD_D = DB_TABLE_PREFIX + "d";

    public static final String DB_SHARD_E = DB_TABLE_PREFIX + "e";

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
