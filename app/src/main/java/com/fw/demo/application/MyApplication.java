package com.fw.demo.application;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by feng on 2019/6/28.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("app", "==========onViewInitFinished is " + b);
            }
        };
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
}
