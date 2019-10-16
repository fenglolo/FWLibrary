package com.fw.demo.utils.file;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 安卓文件的操作总结
 * Created by feng on 2019/10/16.
 *
 * 在Java中文件操作使用的一个类File,该类的一些基本方法如下：
 * File file = new File(path); //该path应为绝对路径名
 * createNewFile();    //创建新文件，如果文件不存在而且创建成功返回true,文件已存在返回false
 * delete();           //删除文件，删除文件或目录成功返回true,如果删除的是目录，该目录必须是空目录才能删除成功
 * exists();           //判断文件或目录是否存在
 * getAbsolutePath();  //返回该文件的绝对路径名
 * getName();          //获取该文件的文件名（与绝对路径名有区别）
 * getParent();        //返回上一级目录名（返回值类型String)
 * getParentFile();    //获取上一级目录（返回值类型File)
 * isDirectory();      //判断该File实例是否是个目录
 * isFile();           //判断该File实例是否是个文件
 * list();             //获取该目录下的子目录名跟文件名（返回值类型String[])
 * listFiles();        //获取该目录下的子目录跟文件（返回值类型File[]）
 * length();           //获取该文件的大小
 * mkdir();            //创建新目录，创建成功返回true
 * renameTo(File);     //将该文件重命名，也可以当作移动文件使用
 */

/**
 * FileInputStream:从文件中读取数据
 * FileOutputStream:往文件中写入数据
 * 构造方法：
 * //获取原文件的FileInputStream流，用于读取原文件中的数据
 * FileInputStream in= new FileInputStream(file);
 * int size = in.read(buffer);
 * //获取目标文件的FileInputStream流，用于将数据写进目标文件中
 * FileOutputStream out = new FileOutputStream(file);
 * out.write(buffer,0,size);
 */

public class FileUtils {

    /**
     * 1、新建目录
     * @param activity
     * @param dirpath
     */
    public void creatFiledir(Context activity, String dirpath){
        File file = new File(dirpath);
        //先是判断该目录是否存在，以存在则不再创建
        if(file.exists()){
            Toast.makeText(activity,
                    "该目录已存在", Toast.LENGTH_SHORT).show();
        }else{
            //新建目录
            if(!file.mkdir()){
                Toast.makeText(activity,
                        "创建失败", Toast.LENGTH_SHORT).show();
                return;
            }else{
                Toast.makeText(activity,
                        "创建成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 2、新建文件
     * @param activity
     * @param filepath
     */
    public void creatFile(Context activity, String filepath){
        File file = new File(filepath);
        //先是判断该文件是否存在，以存在则不再创建
        if(file.exists()){
            Toast.makeText(activity,
                    "该文件已存在", Toast.LENGTH_SHORT).show();
        }else{
            try {
                //新建文件
                if(!file.createNewFile()){
                    Toast.makeText(activity,
                            "创建失败", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(activity,
                            "创建成功", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Toast.makeText(activity,
                        "IOException", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * 3、获取目录下的子目录跟文件
     * @param dirpath
     */
    public void getChildFileAndFiledir(String dirpath){
        File dir = new File(dirpath);
        //先判断dir是否是目录
        if(dir.isDirectory()){
            //获取该目录下的所有文件列表
            File[] filelist = dir.listFiles();
            //判断该列表下的具体某个文件是属于目录还是文件
            for(File file:filelist){
                if(file.isFile()){
                    Log.d("File",file.getName());
                }else if(file.isDirectory()){
                    Log.d("Dir",file.getName());
                }
            }
        }

    }

    /**
     * 4、获取可以打开该类文件的应用程序
     * @param context
     * @param file
     */
    public void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        //获取该文件的文件类型
        String type = getMIMEType(file);
        try{
            //获取可以打开该类文件的应用，并且打开
            intent.setDataAndType(Uri.fromFile(file), type);
            context.startActivity(intent);
        }catch(ActivityNotFoundException e){
            //没有可以打开该类文件的应用，弹出所以应用让用户选择
            intent.setDataAndType(Uri.fromFile(file), "*/*");
            context.startActivity(intent);
        }
    }

    /**
     * 5、获取文件类型
     * @param file
     * @return
     */
    public String getMIMEType(File file) {
        String type = "";
        String name = file.getName();
        //文件扩展名
        String end = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        if (end.equals("m4a") || end.equals("mp3") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("mp4") || end.equals("3gp")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("png") || end.equals("jpeg") || end.equals("bmp") || end.equals("gif")) {
            type = "image";
        } else if (end.equals("txt") || end.equals("doc") ) {
            type = "txt";
        } else if (end.equals("zip") || end.equals("rar") || end.equals("taz") ) {
            type = "zip";
        } else {
            //如果无法直接打开，跳出列表由用户选择
            type += "*";
        }
        type += "/*";
        return type;
    }

    /**
     * 6、重命名或移动文件或目录
     * @param oldName
     * @param newName
     */
    public void renameFileOrFiledir(String oldName, String newName){
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        //先判断旧文件已存在并且新文件名没有被使用，就将旧文件重命名为新文件名
        if(oldFile.exists()&&(!newFile.exists())){
            oldFile.renameTo(newFile);
        }
    }


    /**
     * 7、拷贝文件的操作，将原文件拷贝到目标文件
     * resFile：需要拷贝的文件，不能是目录
     * destFile:目标文件的完整路径名,应为新建的空文件，不能是目录
     */
    public void copyfile(File resFile,File destFile){
        try {
            //获取原文件的FileInputStream流，用于读取原文件中的数据
            FileInputStream resread= new FileInputStream(resFile);
            //获取目标文件的FileInputStream流，用于将数据写进目标文件中
            FileOutputStream destwrite = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024*1024];
            int cnt= 0;
            //判断是否已将原文件中的所有数据都全部写入目标文件
            while(cnt<resFile.length()){
                int a = 0;
                //从原文件中读取数据
                a = resread.read(buffer);
                cnt+=a;
                //将读取到的数据写入目标文件
                if(a<1024*1024)
                {
                    destwrite.write(buffer,0, a);
                }else{
                    destwrite.write(buffer);
                }
            }
            //关闭流
            resread.close();
            destwrite.close();
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    /**
     * 8、拷贝目录
     * 复制目录：先是获取源目录下的子目录跟文件，如果是文件则用复制文件的方法将文件复制进目标目录，
     * 如果是目录则重复前面的操作，直到将该目录下所有的子文件跟子目录全复制完毕。
     *
     * resname:复制源文件或目录的完整路径名
     * destname:需要拷贝到的目标目录的完整路径名
     */
    private void copy(String resname,String destname){
        File destdir= new File(destname);
        File resdir = new File(resname);
        //先是判断源文件的类型
        if(resdir.isDirectory()){
            //判断目标目录是否存在，不存在则新建
            if(!destdir.exists()){
                destdir.mkdir();
            }
            //获取原目录下的子文件跟目录
            String[] list = resdir.list();
            int i=0;
            while(i<list.length){
                //递归调用该方法拷贝子目录中的子文件，这里传的参数应实时变换
                copy(resname+"/"+list[i],destname+"/"+list[i]);
                i++;
            }
        }
        //判断原文件是属于文件类型
        else if(resdir.isFile()){
            try {
                if(!destdir.exists()){
                    destdir.createNewFile();
                }
                //使用上面的复制文件的方法将原文件复制到目标文件
                copyfile(resdir,destdir);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    /**
     * 9、删除文件或目录，跟拷贝目录有点相似，通过递归调用该方法逐一的将目录中的子目录跟子文件都删除掉
     * @param name
     * @return
     */
    public boolean deletefile(String name){
        boolean deleteFlag = true;
        File dir = new File(name);
        try{
            //先是判断删除文件的类型
            if(dir.isDirectory()){
                String[] list =dir.list();
                int i=0;
                while(i<list.length && deleteFlag){
                    //将目录中的子目录跟子文件调用该方法逐一删除掉
                    deleteFlag = deletefile(name+"/"+list[i]);
                    i++;
                }
            }
            //执行到这一步时dir只有两种情况：一、dir是个文件，二、dir是个空目录。该两种情况都可直接使用delete的方法删除掉
            if(!dir.delete()){
                return false;
            }else{
                return true;
            }
        }catch(NullPointerException e){
            return false;
        }
    }






}
