package com.groupone.common.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUitls {
    //默认解压后存放的目录(需要直接拼接上文件夹)
    public static final String DEFAULT_TARGET_PATH="/storage/emulated/0/Download/";

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

    /**
     * 解压
     * @param zipFilePath 要解压的文件路径
     */
    public static void decomPression(String zipFilePath){
        try {
            ZipInputStream zipInputStream=new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = null;
            FileOutputStream outputStream = null;
            BufferedOutputStream bfOutPutStream = null;
            //循环压缩 压缩文件 里的所有文件
            while((zipEntry=zipInputStream.getNextEntry())!=null){
                outputStream=new FileOutputStream("/storage/emulated/0/Download/"+zipEntry.getName());
                bfOutPutStream=new BufferedOutputStream(outputStream);
                byte[] bytes=new byte[1024];
                int len=0;
                while((len=zipInputStream.read(bytes))!=-1){
                    bfOutPutStream.write(bytes,0,len);
                }
                bfOutPutStream.flush();
                outputStream.flush();
            }
            bfOutPutStream.close();
            outputStream.close();
            zipInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
