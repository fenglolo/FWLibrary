package com.fw.demo.activity.gifImageView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fw.demo.BaseActivity;
import com.fw.demo.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * gifImageView使用：加载png和gif图片
 * Created by feng on 2019/9/4.
 */

public class GifImageViewActivity extends BaseActivity implements View.OnClickListener {
    private GifImageView gifImageView1,gifImageView2;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_gifiamgeview;
    }

    @Override
    protected void initGui() {
        gifImageView1 = findViewById(R.id.gifimg_1);
        gifImageView2 = findViewById(R.id.gifimg_2);
    }

    @Override
    protected void initAction() {
    }

    @Override
    protected void initData() {
        try {
            // 如果加载的是gif动图，第一步需要先将gif动图资源转化为GifDrawable
            // 将gif图资源转化为GifDrawable
            GifDrawable gifDrawable = new GifDrawable(getResources(),R.mipmap.loding);
            // gif1加载一个动态图gif
            gifImageView1.setImageDrawable(gifDrawable);

            // 如果是普通的图片资源，就像Android的ImageView set图片资源一样简单设置进去即可。
            // gif2加载一个普通的图片（如png，bmp，jpeg等等）
            gifImageView2.setImageResource(R.mipmap.ic_launcher);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }


}
