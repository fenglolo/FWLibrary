package com.fw.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.demo.activity.gifImageView.GifImageViewActivity;
import com.fw.demo.activity.glide.GlideActivity;
import com.fw.demo.activity.TestActivity;
import com.fw.demo.activity.animation.AnimationActivity;
import com.fw.demo.activity.textRoll.TextRollActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_glide;
    private Button btn_gifImageview;
    private Button btn_ani;
    private Button btn_test;
    private Button btn_roll;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initGui() {
        btn_glide = findViewById(R.id.btn_glide);
        btn_gifImageview = findViewById(R.id.btn_gifImageview);
        btn_ani = findViewById(R.id.btn_anil);
        btn_test = findViewById(R.id.btn_test);
        btn_roll = findViewById(R.id.btn_roll);
    }

    @Override
    protected void initAction() {
        btn_glide.setOnClickListener(this);
        btn_gifImageview.setOnClickListener(this);
        btn_ani.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        btn_roll.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_glide://glide组件使用
                gotoActviity(GlideActivity.class);
                break;
            case R.id.btn_gifImageview://btn_gifImageview使用
                gotoActviity(GifImageViewActivity.class);
                break;
            case R.id.btn_anil://安卓动画
                gotoActviity(AnimationActivity.class);
                break;
            case R.id.btn_test://ceshi
                gotoActviity(TestActivity.class);
                break;
            case R.id.btn_roll://安卓滚动字幕
                gotoActviity(TextRollActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * Activity跳转
     * @param cls
     */
    public void gotoActviity(Class cls){
        startActivity(new Intent(this,cls));
    }
}
