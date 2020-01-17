package com.fw.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fw.demo.activity.TestActivity;
import com.fw.demo.activity.animation.AnimationActivity;
import com.fw.demo.activity.gifImageView.GifImageViewActivity;
import com.fw.demo.activity.glide.GlideActivity;
import com.fw.demo.activity.md5.MdfiveActivity;
import com.fw.demo.activity.test.TestOneActivity;
import com.fw.demo.activity.textRoll.TextRollActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_glide;
    private Button btn_gifImageview;
    private Button btn_ani;
    private Button btn_test;
    private Button btn_roll;
    private Button btn_md5;

    private EditText ed;
    private TextView tv_ed;
    private Button btn_ed;

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
        btn_md5 = findViewById(R.id.btn_md5);

        ed = findViewById(R.id.ed);
        tv_ed = findViewById(R.id.tv_ed);
        btn_ed = findViewById(R.id.btn_ed);
    }

    @Override
    protected void initAction() {
        btn_glide.setOnClickListener(this);
        btn_gifImageview.setOnClickListener(this);
        btn_ani.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        btn_roll.setOnClickListener(this);
        btn_md5.setOnClickListener(this);

        btn_ed.setOnClickListener(this);
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
            case R.id.btn_ed://获取时间
                long l = Long.valueOf(ed.getText().toString().trim());
                getTime(l);
                break;
            case R.id.btn_md5://生成md5
//                gotoActviity(MdfiveActivity.class);
                gotoActviity(TestOneActivity.class);
                break;
            default:
                break;
        }
    }

    private void getTime(long time){
        Date nowTime = new Date(time);
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        tv_ed.setText(retStrFormatNowDate);
    }

    /**
     * Activity跳转
     * @param cls
     */
    public void gotoActviity(Class cls){
        startActivity(new Intent(this,cls));
    }
}
