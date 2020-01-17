package com.fw.demo.activity.test;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

/**
 * 沉浸式状态栏，沉浸式全屏
 * Created by feng on 2020/1/15.
 */

public class TestOneActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_test_new;
    }

    @Override
    protected void initGui() {
//        init1();
//        init2();
//        init3();
//        init4();
    }

    //全屏：状态栏隐藏
    private void init1(){
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
    }

    //全屏：状态栏显示
    private void init2(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //全屏：状态栏隐藏、导航栏隐藏 (有缺陷，触摸一下屏幕就会出现状态栏)
    private void init3(){
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
    }

    //全屏：状态栏显示、导航栏隐藏
    private void init4(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 使用沉浸式模式，那么只需要重写Activity的onWindowFocusChanged()方法
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {//只有在Android 4.4及以上系统才支持沉浸式模式
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
