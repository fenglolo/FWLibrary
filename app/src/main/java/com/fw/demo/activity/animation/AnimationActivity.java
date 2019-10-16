package com.fw.demo.activity.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2019/10/16.
 */

public class AnimationActivity extends BaseActivity {

    private ImageView imageView;
    private Button button;
    private AnimationDrawable mAnimationDrawable;

    private int type = 0;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initGui() {
        mAnimationDrawable = new AnimationDrawable();
        imageView = findViewById(R.id.iv_ani);
        button = findViewById(R.id.btn_ani);
    }

    @Override
    protected void initAction() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 0){
                    animLoadingStart();
                }else {
                    animLoadingEnd();
                }

            }
        });
    }

    @Override
    protected void initData() {
        imageView.setBackgroundDrawable(getAnimationDrawable(mAnimationDrawable));
    }

    private AnimationDrawable getAnimationDrawable(AnimationDrawable mAnimationDrawable) {
        int duration = 50;
        List<Bitmap> list = new ArrayList<>();
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00001));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00002));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00003));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00004));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00005));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00006));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00007));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00008));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00009));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00010));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00011));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00012));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00013));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00014));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00015));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00016));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00017));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00018));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00019));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00020));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00021));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00022));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00023));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00024));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00025));
        list.add(BitmapFactory.decodeResource(getResources(),R.mipmap.loading_00026));
        for(int i=0;i<list.size();i++){
            mAnimationDrawable.addFrame(new BitmapDrawable(list.get(i)), duration);
        }

//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00000), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00001), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00002), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00003), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00004), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00005), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00006), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00007), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00008), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00009), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00010), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00011), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00012), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00013), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00014), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00015), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00016), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00017), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00018), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00019), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00020), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00021), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00022), duration);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.loading_00023), duration);
        mAnimationDrawable.setOneShot(false);
        return mAnimationDrawable;
    }

    //动画开始
    public void animLoadingStart() {
        if (mAnimationDrawable != null) {
            mAnimationDrawable.start();
        }
        type = 1;
    }

    //动画结束
    public void animLoadingEnd() {
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
        type = 0;
    }
}
