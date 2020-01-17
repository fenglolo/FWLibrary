package com.fw.demo.activity.md5;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.demo.BaseActivity;
import com.fw.demo.R;
import com.yitong.mbank.util.security.Md5Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 把assets目录下的文件复制到本地，获取本地文件的md5值
 * Created by feng on 2020/1/6.
 */

public class MdfiveActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_copy, btn_output;
    private TextView tv_zip_md5, tv_output;
    private EditText ed_input;

    public String downloadPath1 = "/one";
    public String downloadPath2 = "/two";
    public String downloadPath3 = "/three";
    public String downloadPath4 = "/four";

    private String md5 = "";

    @Override
    protected int getContentLayout() {
        return R.layout.activity_md;
    }

    @Override
    protected void initGui() {
        btn_copy = findViewById(R.id.btn_copy);
        tv_zip_md5 = findViewById(R.id.tv_zip_md5);
        ed_input = findViewById(R.id.ed_input);
        btn_output = findViewById(R.id.btn_output);
        tv_output = findViewById(R.id.tv_output);
    }

    @Override
    protected void initAction() {
        btn_copy.setOnClickListener(this);
        btn_output.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        copyFilesFassets("one.zip", this.getFilesDir().getAbsolutePath() + downloadPath1);
        copyFilesFassets("two.zip", this.getFilesDir().getAbsolutePath() + downloadPath2);
        copyFilesFassets("three.zip", this.getFilesDir().getAbsolutePath() + downloadPath3);
        copyFilesFassets("four.zip", this.getFilesDir().getAbsolutePath() + downloadPath4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_copy:
                getMd5(this.getFilesDir().getAbsolutePath() + downloadPath1);
                getMd5(this.getFilesDir().getAbsolutePath() + downloadPath2);
                getMd5(this.getFilesDir().getAbsolutePath() + downloadPath3);
                getMd5(this.getFilesDir().getAbsolutePath() + downloadPath4);
                if (!TextUtils.isEmpty(md5)) {
                    tv_zip_md5.setText(md5);
                } else {
                    tv_zip_md5.setText("失败");
                }
                break;
            case R.id.btn_output:
                if (!TextUtils.isEmpty(ed_input.getText().toString())) {
                    tv_output.setText(Md5Util.encode(ed_input.getText().toString()));
                } else {
                    Toast.makeText(this, "请输入...", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //-------------------------------------------------

    private void getMd5(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            md5 += Md5Util.fileMd5(file) + "               ";
        }
    }

    /**
     * copy assets目录下的文件到本地
     *
     * @param oldPath assets目录
     * @param newPath 本地目录
     */
    public void copyFilesFassets(String oldPath, String newPath) {
        try {
            String fileNames[] = activity.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = activity.getAssets().open(oldPath);
                File file = new File(newPath);
                if(!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
