package com.fw.demo.activity.webview;

import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;

/**
 * Created by feng on 2020/1/13.
 */

public class WebviewActivity extends BaseActivity {

    TextView tv_title;
    WebView webView;

    @Override
    protected int getContentLayout() {
        return R.layout.webview;
    }

    @Override
    protected void initGui() {
        tv_title = findViewById(R.id.tv_title);
        webView = findViewById(R.id.web);
    }

    @Override
    protected void initAction() {
        /**
         * 添加调试
         */
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true); // 使用localStorage
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true); // 设置页面自适应屏幕
        webSettings.setJavaScriptEnabled(true); // 支持javascript
        webSettings.setBlockNetworkImage(false);//同步请求图片
        webSettings.setNeedInitialFocus(false); // 阻止内部节点获取焦点
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDefaultFontSize(16);
        webSettings.setTextZoom(100);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不使用webview缓存
        // 修复加载资源失败BUG start add by dlzhang 2015-03-26
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        // 修复加载资源失败BUG end
        webSettings.setSavePassword(false);
        String useAgent=webSettings.getUserAgentString();
        webSettings.setUserAgentString(useAgent+" ZJRCU");

        // SDK3.0以上开启硬件加速，部分机器
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//			webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//		}
        // android 5.0以上默认不支持Mixed Content
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.requestFocusFromTouch();
        webView.setFocusable(true);
        webView.setLongClickable(true);
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webView.setOnLongClickListener(new WebView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webView.setWebChromeClient(wvcc);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(255);

    }

    @Override
    protected void initData() {
        webView.loadUrl("https://blog.csdn.net/mlj1668956679/article/details/51770095");
    }

    private WebChromeClient wvcc = new WebChromeClient() {

        @Override
        public boolean onJsAlert(WebView view, String url,
                                 String message, final JsResult result) {
//            DialogUtils.showDialogOneBtn(context, message, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    result.confirm();
//                }
//            });
            return true;
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            Logs.i("ANDROID_LAB", "TITLE=" + title);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//            mUploadMessage = uploadMsg;
//            context.startActivityForResult(createDefaultIntent(), RequestCodes.FILECHOOSER_RESULTCODE);
        }

        // For Android 3.0+
        public void openFileChooser( ValueCallback uploadMsg, String acceptType ) {
//            mUploadMessage = uploadMsg;
//            context.startActivityForResult(createDefaultIntent(),
//                    RequestCodes.FILECHOOSER_RESULTCODE);
        }
        //For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
//            mUploadMessage = uploadMsg;
//            context.startActivityForResult(createDefaultIntent(), RequestCodes.FILECHOOSER_RESULTCODE );
        }
        // For Android 5.0+
        public boolean onShowFileChooser (WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//            mUploadCallbackAboveL = filePathCallback;
//            AndPermissionUtil.requestPermission(context,
//                    Manifest.permission.CAMERA,
//                    new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            context.startActivityForResult(createDefaultIntent(),
//                                    RequestCodes.FILECHOOSER_RESULTCODE);
//                        }
//                    }, new Action<List<String>>() {
//                        @Override
//                        public void onAction(List<String> data) {
//                            if (AndPermission.hasAlwaysDeniedPermission(context,
//                                    Manifest.permission.CAMERA)) {
//                                GoldDialogUtils.showDialog(context,
//                                        context.getResources().getString(R.string.permission_action_camera),
//                                        new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                AndroidUtil.toSelfSetting(context);
//                                            }
//                                        });
//                                return;
//                            }
//                            if (ListUtils.isEmpty(data) && data.contains(Manifest.permission.CAMERA)) {
//                                GoldDialogUtils.showDialogOkBold(context,
//                                        context.getResources().getString(R.string.permission_action_camera));
//                            }
//                        }
//                    });
            return true;
        }
    };
}
