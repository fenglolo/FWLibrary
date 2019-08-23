package com.fw.demo.utils.http;

import com.fw.demo.utils.http.httpInterface.ResponseHandlerInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by feng on 2019/7/24.
 */

public class AsyncHttpClient {
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    private static final int DEFAULT_WRITE_TIMEOUT = 10000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    private String requestCharset;
    private final Interceptor headerInterceptor;
    private final Map<String, String> clientHeaderMap;
    private OkHttpClient httpClient;

    public AsyncHttpClient() {
        this(DEFAULT_CONNECT_TIMEOUT, DEFAULT_WRITE_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public AsyncHttpClient(int connectTime, int writeTime, int readTime) {
        this.requestCharset = DEFAULT_CHARSET;
        this.headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Headers.Builder headersBuilder = originalRequest.headers().newBuilder();
                String header;
                if (AsyncHttpClient.this.clientHeaderMap != null && AsyncHttpClient.this.clientHeaderMap.size() > 0) {
                    for (Iterator var4 = AsyncHttpClient.this.clientHeaderMap.keySet().iterator(); var4.hasNext(); headersBuilder.add(header, (String) AsyncHttpClient.this.clientHeaderMap.get(header))) {
                        header = (String) var4.next();
                        String overwritten = headersBuilder.get(header);
                        if (overwritten != null) {
                            headersBuilder.removeAll(header);
                        }
                    }
                }

                Request newRequest = originalRequest.newBuilder().headers(headersBuilder.build()).build();
                Response response = chain.proceed(newRequest);
                return response;
            }
        };
        this.clientHeaderMap = new HashMap();
        this.httpClient = (new okhttp3.OkHttpClient.Builder())
                .connectTimeout((long) connectTime, TimeUnit.MILLISECONDS)
                .writeTimeout((long) writeTime, TimeUnit.MILLISECONDS)
                .readTimeout((long) readTime, TimeUnit.MILLISECONDS)
                .addInterceptor(this.headerInterceptor)
                .retryOnConnectionFailure(false)
                .build();
    }

    public void removeAllHeaders() {
        this.clientHeaderMap.clear();
    }

    public void addHeader(String header, String value) {
        this.clientHeaderMap.put(header, value);
    }

    public void removeHeader(String header) {
        this.clientHeaderMap.remove(header);
    }

    public OkHttpClient getHttpClient() {
        return this.httpClient;
    }

    public void setCharset(String charset) {
        this.requestCharset = charset;
    }

    public void get(String url, ResponseHandlerInterface responseHandlerInterface) {
        Request request = (new okhttp3.Request.Builder())
                .url(url.toString())
                .build();
        this.httpClient
                .newCall(request)
                .enqueue(new HttpCallback(responseHandlerInterface));
    }

    public void post(String url, String postbody, String contentType, ResponseHandlerInterface responseHandler) {
        MediaType mediaType = MediaType.parse(contentType + "; charset=" + this.requestCharset);
        Request request = (new okhttp3.Request.Builder())
                .url(url)
                .post(RequestBody.create(mediaType, postbody))
                .build();
        this.httpClient
                .newCall(request)
                .enqueue(new HttpCallback(responseHandler));
    }

    public void cancelRequests() {
        this.httpClient.dispatcher().cancelAll();
    }

}
