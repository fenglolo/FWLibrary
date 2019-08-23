package com.fw.demo.utils.http;

/**
 * Created by feng on 2019/7/24.
 */

public class APPRequestClient {

    private static final String DEFAULT_CONTENTTYPE = "application/json";
    private static final String DEFAULT_CHARSET = "utf-8";

    private static int defaultConnectTime = 30*1000;
    private static int defaultWriteTime = 60*1000;
    private static int defaultReadTime = 60*1000;

    private static AsyncHttpClient client;

    static {
        client = createDefaultClient();
    }

    /**
     * 创建一个基本设置的异步请求对象
     */
    private static AsyncHttpClient createDefaultClient() {
        AsyncHttpClient newClient = new AsyncHttpClient(defaultConnectTime, defaultWriteTime, defaultReadTime);
        newClient.addHeader("user-agent", "android");
        newClient.setCharset(DEFAULT_CHARSET);
        return newClient;
    }
}
