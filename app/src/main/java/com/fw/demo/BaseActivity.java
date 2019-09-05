package com.fw.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Created by feng on 2019/6/28.
 */

public abstract class BaseActivity extends FragmentActivity {

    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getContentLayout() != 0) {
            setContentView(getContentLayout());
        }
        activity = this;
        initGui();
        initAction();
        initData();
    }

    /**
     * 设置布局文件
     */
    protected abstract int getContentLayout();

    /**
     * 初始化UI
     *
     */
    protected abstract void initGui();

    /**
     * 初始化事件
     */
    protected abstract void initAction();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
