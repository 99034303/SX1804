package com.groupone.common.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUitls {
    /**
     * 压缩
     * @param source 要压缩的文件路径
     * @param targetPath 压缩后输出的路径
     */
    public static void executeZip(String source,String targetPath){
        try {
            File file=new File(source);
            //判断文件路径
            if(!file.exists()||!file.isFile()){
                return;
            }
            ZipOutputStream zipStream=new ZipOutputStream(new FileOutputStream(targetPath));
            zipStream.putNextEntry(new ZipEntry(file.getName()));
            BufferedOutputStream bfStream=new BufferedOutputStream(zipStream);
            FileInputStream in=new FileInputStream(source);
            byte[] bytes=new byte[1024];
            int len=0;
            //循环写入
            while((len=in.read(bytes))!=-1){
                bfStream.write(bytes,0,len);
            }
            bfStream.flush();
            bfStream.close();
            zipStream.finish();
            zipStream.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
