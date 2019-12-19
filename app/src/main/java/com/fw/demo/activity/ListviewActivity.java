package com.fw.demo.activity;

import android.widget.Adapter;
import android.widget.ListView;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

import java.util.List;

/**
 * Created by feng on 2019/11/5.
 */

public class ListviewActivity extends BaseActivity {
    private ListView listView;
    private Adapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_listview;
    }

    @Override
    protected void initGui() {
        listView = findViewById(R.id.lv_view);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
