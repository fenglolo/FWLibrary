package com.fw.demo.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

/**
 * Created by feng on 2019/10/17.
 */

public class TestActivity extends BaseActivity{

    TextView textView;
    EditText editText;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initGui() {
        textView = findViewById(R.id.tv_copy);
        editText = findViewById(R.id.et_copy);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
