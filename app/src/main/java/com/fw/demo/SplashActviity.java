package com.fw.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by feng on 2019/6/28.
 */

public class SplashActviity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void initData() {

    }
}
