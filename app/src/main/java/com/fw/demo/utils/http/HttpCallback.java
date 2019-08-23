package com.fw.demo.utils.http;


import com.fw.demo.utils.http.httpInterface.ResponseHandlerInterface;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by feng on 2019/7/24.
 */

public class HttpCallback implements Callback {

    private final ResponseHandlerInterface responseHandler;

    public HttpCallback(ResponseHandlerInterface responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        this.responseHandler.sendFailureMessage(0, (Headers)null, (byte[])null, e);
        this.responseHandler.sendFinishMessage();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            this.responseHandler.sendResponseMessage(response);
            this.responseHandler.sendFinishMessage();
        } catch (Exception var4) {
            this.responseHandler.sendFailureMessage(0, (Headers)null, (byte[])null, var4);
        }
    }
}
