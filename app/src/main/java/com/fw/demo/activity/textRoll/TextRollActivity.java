package com.fw.demo.activity.textRoll;

import android.graphics.Color;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

/**
 * 安卓滚动字幕:textSwitcher使用，imageSwitcher使用，
 * Created by feng on 2019/12/16.
 */

public class TextRollActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_ts,tv_is,tv_vp;

    private TextSwitcher textSwitcher = null;
    private int times = 0;
    private TextSwitcher textSwitcher2 = null;
    private int marker = 0;
    private ImageSwitcher imageSwitcher;
    private int img = 0;
    private ViewFlipper viewFlipper = null;
    private ViewFlipper viewFlipper2 = null;

    final String[] strs = new String[] {
            "C++ 多态性 运算符重载",
            "C++ 多态性 虚函数、抽象类（一）",
            "C++ 多态性 虚函数、抽象类（二）",
            "C++ 作用域分辨符"
    };

    final int[] imgs = new int[]{
            R.mipmap.ani_cat,
            R.mipmap.ani_cow,
            R.mipmap.ani_dog,
            R.mipmap.ani_dragon,
            R.mipmap.ani_fireflies,
            R.mipmap.ani_fox
    };

    private int delayTime = 2000;
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            nextView();
            handler.postDelayed(task, delayTime);
        }
    };
    private Runnable task2 = new Runnable() {
        @Override
        public void run() {
            nextImageView();
            handler.postDelayed(task2, delayTime);
        }
    };

    @Override
    protected int getContentLayout() {
        return R.layout.activity_rolling;
    }

    @Override
    protected void initGui() {
        tv_ts = findViewById(R.id.tv_ts);
        tv_is = findViewById(R.id.tv_is);
        tv_vp = findViewById(R.id.tv_vp);
        initView();
    }

    private void initView(){
        textSwitcher = findViewById(R.id.ts);
        textSwitcher2 = findViewById(R.id.ts_2);
        imageSwitcher = findViewById(R.id.is);
        viewFlipper = findViewById(R.id.vf);
        viewFlipper2 = findViewById(R.id.vf_2);
    }

    @Override
    protected void initAction() {
        tv_ts.setOnClickListener(this);
        tv_is.setOnClickListener(this);
        tv_vp.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //-------------------------------------------------------------------

    public void next(View view) {
        textSwitcher.setText(strs[times++ % strs.length]);
    }

    public void start() {
        stop();
        handler.postDelayed(task, delayTime);
    }

    public void stop(){
        handler.removeCallbacks(task);
    }

    public void start2() {
        stop2();
        handler.postDelayed(task2, delayTime);
    }

    public void stop2(){
        handler.removeCallbacks(task2);
    }

    private void nextView() {
        marker = ++marker % strs.length;
        textSwitcher2.setText(strs[marker]);
    }

    private void nextImageView(){
        img = ++img % imgs.length;
        imageSwitcher.setBackground(getResources().getDrawable(imgs[img]));
    }

    private ImageView getImageView(int id){
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(id);
        return imageView;
    }

    //------------------------------Visibility-------------------------------------

    private void setVisibilityGone(){
        textSwitcher.setVisibility(View.GONE);
        textSwitcher2.setVisibility(View.GONE);
        imageSwitcher.setVisibility(View.GONE);
        viewFlipper.setVisibility(View.GONE);
    }

    //------------------------------onClick-------------------------------------
    private void startTS(){
        //1:点击切换
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(TextRollActivity.this);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                tv.setTextColor(Color.MAGENTA);
                return tv;
            }
        });
        next(null);

        //2：动态切换
        textSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView t = new TextView(TextRollActivity.this);
                return t;
            }
        });
        textSwitcher2.setText(strs[marker]);
        start();
    }

    private void startIS(){
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(TextRollActivity.this);
            }
        });
        imageSwitcher.setBackground(getResources().getDrawable(R.mipmap.ani_dog));
        start2();
    }

    private void startVP(){

        //1：布局默认imageview
        if (viewFlipper.getChildCount() > 1){
            viewFlipper.startFlipping();
        }else {
            viewFlipper.stopFlipping();
        }

        //2：动态添加布局
        viewFlipper2.addView(getImageView(R.mipmap.ani_cow));
        viewFlipper2.addView(getImageView(R.mipmap.ani_dog));
        viewFlipper2.addView(getImageView(R.mipmap.ani_dragon));
        viewFlipper2.addView(getImageView(R.mipmap.ani_fox));
        if (viewFlipper2.getChildCount() > 1){
            viewFlipper2.startFlipping();
        }else {
            viewFlipper2.stopFlipping();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_ts://TextSwitcher
                setVisibilityGone();
                textSwitcher.setVisibility(View.VISIBLE);
                textSwitcher2.setVisibility(View.VISIBLE);

                startTS();
                break;
            case R.id.tv_is://ImageSwitcher
                setVisibilityGone();
                imageSwitcher.setVisibility(View.VISIBLE);

                startIS();
                break;
            case R.id.tv_vp://ViewFlipper
                setVisibilityGone();
                viewFlipper.setVisibility(View.VISIBLE);

                startVP();
                break;
            default:
                break;
        }
    }
}
