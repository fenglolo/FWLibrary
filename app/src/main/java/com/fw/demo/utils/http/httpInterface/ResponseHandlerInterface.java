package com.fw.demo.utils.http.httpInterface;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by feng on 2019/7/24.
 */

public interface ResponseHandlerInterface {

    void sendResponseMessage(Response var1) throws IOException;

    void sendFinishMessage();

    void sendSuccessMessage(int i, Headers headers, byte[] bytes);

    void sendFailureMessage(int i, Headers headers, byte[] bytes, Throwable throwable);
}
