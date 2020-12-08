package com.wmc.log;

import android.app.Application;
import android.database.DefaultDatabaseErrorHandler;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull final Throwable e) {
                new ThreadPoolExecutor(1,1,5, TimeUnit.SECONDS,new SynchronousQueue<Runnable>()).execute(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(LogApplication.this, "程序异常，即将推出", Toast.LENGTH_SHORT).show();
                        Looper.loop();

                        //保存错误信息
                        saveThrowable(e);

                        //退出程序
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    }
                });
            }
        });
    }

    private void saveThrowable(Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace();
        Throwable cause = e.getCause();
        while (cause!=null){
            cause.printStackTrace(printWriter);
            cause=e.getCause();
        }
        //关闭
        printWriter.close();
        //文件名称
        String fileName="Throwable->"+System.currentTimeMillis()+".log";
        //判断SD卡是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //文件储存的位置
            String path=Environment.getExternalStorageDirectory().getPath()+"/logInfo";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path + fileName);
                fileOutputStream.write(printWriter.toString().getBytes());
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
