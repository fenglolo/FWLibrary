package com.fw.demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.fw.demo.activity.GlideActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_glide;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initGui() {
        btn_glide = findViewById(R.id.btn_glide);
    }

    @Override
    protected void initAction() {
        btn_glide.setOnClickListener(this);
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
