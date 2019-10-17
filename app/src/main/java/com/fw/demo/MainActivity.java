package com.fw.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fw.demo.activity.GifImageViewActivity;
import com.fw.demo.activity.GlideActivity;
import com.fw.demo.activity.TestActivity;
import com.fw.demo.activity.animation.AnimationActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_glide,btn_gifImageview,btn_ani,btn_test;

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
    }

    @Override
    protected void initAction() {
        btn_glide.setOnClickListener(this);
        btn_gifImageview.setOnClickListener(this);
        btn_ani.setOnClickListener(this);
        btn_test.setOnClickListener(this);
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
