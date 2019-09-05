package com.fw.demo.activity;

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

/**
 * Created by feng on 2019/9/4.
 */

public class GlideActivity extends BaseActivity implements View.OnClickListener {

    public String url_1 = "http://a2.att.hudong.com/36/46/05300000993302129747461208128.jpg";
    public String url_2 = "http://pic5.nipic.com/20091228/3648835_080133069058_2.jpg";
    public String url_3 = "http://pic5.nipic.com/20091228/3648835_080348088117_2.jpg";
    public String url_4 = "http://pic25.nipic.com/20121210/7447430_215258605000_2.jpg";
    public String url_5 = "http://pic.baike.soso.com/p/20131220/20131220231704-1138887644.jpg";

    private Button btn_frist,btn_second;
    private LinearLayout ll_1,ll_2;

    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10;
    private ImageView img_glide, img_glide2;
    private TextView tv_glide;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initGui() {
        btn_frist = findViewById(R.id.btn_frist);
        btn_second = findViewById(R.id.btn_second);
        ll_1 = findViewById(R.id.ll_frist);
        ll_2 = findViewById(R.id.ll_second);

        btn_1 = findViewById(R.id.btn_glide_1);
        btn_2 = findViewById(R.id.btn_glide_2);
        btn_3 = findViewById(R.id.btn_glide_3);
        btn_4 = findViewById(R.id.btn_glide_4);
        btn_5 = findViewById(R.id.btn_glide_5);
        btn_6 = findViewById(R.id.btn_glide_6);
        btn_7 = findViewById(R.id.btn_glide_7);
        btn_8 = findViewById(R.id.btn_glide_8);
        btn_9 = findViewById(R.id.btn_glide_9);
        btn_10 = findViewById(R.id.btn_glide_10);

        img_glide = findViewById(R.id.img_glide);
        img_glide2 = findViewById(R.id.img_glide2);

        tv_glide = findViewById(R.id.tv_glide);
    }

    @Override
    protected void initAction() {
        btn_frist.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_10.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_frist:
                ll_1.setVisibility(View.VISIBLE);
                ll_2.setVisibility(View.GONE);
                break;
            case R.id.btn_second:
                ll_1.setVisibility(View.GONE);
                ll_2.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_glide_1://基本方法
                Glide.with(activity)
                        .load(url_1)
                        .into(img_glide);
                break;
            case R.id.btn_glide_2://占位图设置
                Glide.with(activity)
                        .load(url_2)
                        .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                        .error(R.mipmap.ic_launcher_round)//图片加载失败后，显示的图片
                        .into(img_glide);

                tv_glide.setText("placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片\n" +
                        "error(R.mipmap.ic_launcher_round)//图片加载失败后，显示的图片");
                break;
            case R.id.btn_glide_3://缩略图设置1
                Glide.with(activity)
                        .load(url_3)
                        .thumbnail(0.2f)//传入 0.2f 作为参数，Glide 将会显示原始图片的20%的大小
                        .into(img_glide2);

                tv_glide.setText("thumbnail(0.2f)//传入 0.2f 作为参数，Glide 将会显示原始图片的20%的大小\n\n" +
                        "需要确保 ImageView 的 ScaleType 设置的正确");
                break;
            case R.id.btn_glide_4://缩略图设置2
                // setup Glide request without the into() method
                DrawableRequestBuilder<String> thumbnailRequest = Glide.with(activity).load(url_1);
                // pass the request as a a parameter to the thumbnail request
                Glide.with(activity)
                        .load(url_4)
                        .thumbnail(thumbnailRequest)//加载网络图片
                        .into(img_glide2);

                tv_glide.setText("DrawableRequestBuilder<String> thumbnailRequest = Glide.with(activity).load(url_1);\n" +
                        "thumbnail(thumbnailRequest)//加载网络图片");

                break;
            case R.id.btn_glide_5://开启or关闭动画设置
                // 动画效果可以让图片加载变得更加的平滑，crossFade() 方法强制开启 Glide 默认的图片淡出淡入动画，
                // 当前版本3.7.0是默认开启的。crossFade() 还有一个重载方法 crossFade(int duration)。
                // 可以控制动画的持续时间，单位ms。动画默认的持续时间是300ms。
                // 既然可以添加动画，那肯定就可以设置没有任何淡出淡入效果，调用 dontAnimate()
                Glide.with(activity)
                        .load(url_1)
                        .crossFade()//或者使用 dontAnimate() 关闭动画
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher_round)
                        .into(img_glide);

                tv_glide.setText("动画效果可以让图片加载变得更加的平滑，crossFade() 方法强制开启 Glide 默认的图片淡出淡入动画，\n" +
                        "当前版本3.7.0是默认开启的。crossFade() 还有一个重载方法 crossFade(int duration)。\n" +
                        "可以控制动画的持续时间，单位ms。动画默认的持续时间是300ms。\n" +
                        "既然可以添加动画，那肯定就可以设置没有任何淡出淡入效果，调用 dontAnimate()");
                break;
            case R.id.btn_glide_6://图片大小与裁剪
                // override(width,height) 方法，在图片显示到 ImageView 之前，重新改变图片大小
                Glide.with(activity)
                        .load(url_2)
                        .override(50, 30)//这里的单位是px
                        .into(img_glide2);

                tv_glide.setText("override(width,height) 方法，在图片显示到 ImageView 之前，重新改变图片大小..(这里的单位是px)");
                break;
            case R.id.btn_glide_7://缓存
//                默认 内存缓存、磁盘缓存都开启
//                DiskCacheStrategy 的枚举意义：
//                DiskCacheStrategy.NONE 什么都不缓存
//                DiskCacheStrategy.SOURCE 只缓存全尺寸图
//                DiskCacheStrategy.RESULT 只缓存最终的加载图
//                DiskCacheStrategy.ALL 缓存所有版本图（默认行为）

                Glide.with(activity)
                        .load(url_3)
                        .skipMemoryCache(true) //关闭内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭磁盘缓存
                        .into(img_glide);

                tv_glide.setText("默认 内存缓存、磁盘缓存都开启\nDiskCacheStrategy.NONE 什么都不缓存\n" +
                        "DiskCacheStrategy.SOURCE 只缓存全尺寸图\nDiskCacheStrategy.RESULT 只缓存最终的加载图" +
                        "\nDiskCacheStrategy.ALL 缓存所有版本图（默认行为）");

                break;
            case R.id.btn_glide_8://设置 下载图片 优先级
                //设置 HIGH 优先级
                Glide.with(activity)
                        .load(url_5)
                        .priority(Priority.HIGH)
                        .into(img_glide);
                //设置 LOW 优先级
                Glide.with(activity)
                        .load(url_1)
                        .priority(Priority.LOW)
                        .into(img_glide);

                tv_glide.setText("Glide 可以调用 .priority() 方法配合 Priority 枚举来设置图片加载的优先级。\n" +
                        "Priority.LOW\n" +
                        "Priority.NORMAL\n" +
                        "Priority.HIGH\n" +
                        "Priority.IMMEDIAT\n" +
                        "这里有一点需要注意，优先级并不是完全严格遵守的。Glide 将会用他们作为一个准则，尽可能的处理这些请求" +
                        "，但是不能保证所有的图片都会按照所有要求的顺序加载。");

                break;
            case R.id.btn_glide_9://显示gif
                tv_glide.setText("显示 GIf 对于 Glide 来说一个比较特别的功能（至少 Picasso 暂时还不行）而且使用起来非常简单" +
                        "\n\nGlide.with( context )\n" +
                        "    .load( gifUrl )\n" +
                        "    .asGif()\n" +
                        "    .error( R.drawable.error )\n" +
                        "    .into( imageView );\n" +
                        "果图片类型不是 Gif 图的话就会当作 load 失败来处理，因此 error() 会被回调。即使这个url的图片是好的，也是不会显示的\n\n" +
                        "Glide.with( context )\n" +
                        "    .load( gifUrl )\n" +
                        "    .asBitmap()\n" +
                        "    .error( R.drawable.error )\n" +
                        "    .into( imageView );\n" +
                        "仅仅是显示 Gif 的第一帧图像，这样就可以保证图片的正常显示了");
                break;
            case R.id.btn_glide_10://显示video
                tv_glide.setText("String filePath = \"/storrage/emulated/0/Pictures/video.mp4\";\n" +
                        "Glide.with( context )\n" +
                        "    .load( Uri.fromFile( new File( filePath ) ) )\n" +
                        "    .into( imageView );\n" +
                        "\n" +
                        "Glide 还能显示视频！But...只能够显示手机本地的视频");
                break;
            default:
                break;
        }
    }


}
