package com.wmc.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author 魏铭池
 */
public class ImageUtils {
    private static volatile ImageUtils imageUtils;
    private boolean flag=false;
    private Context context;
    private String url;
    private ImageView imageView;
    private int drawableId;

    public ImageUtils(Context context, String url, ImageView imageView,boolean flag) {
        this.context = context.getApplicationContext();
        this.url = url;
        this.imageView = imageView;
        this.flag=false;
    }

    public ImageUtils(Context context,  int drawableId,ImageView imageView,boolean flag) {
        this.context = context.getApplicationContext();
        this.imageView = imageView;
        this.drawableId = drawableId;
        this.flag=false;
    }

    /**
     * 根据网络资源加载一张朴实无华的照片
     * @param context 场景
     * @param url 照片的网络地址
     * @param imageView 照片对象
     * @return 返回当前类
     */
    public static ImageUtils singleImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
        if (imageUtils==null){
            synchronized (ImageUtils.class){
                if (imageUtils==null){
                    imageUtils=new ImageUtils(context,url,imageView,true);
                    return imageUtils;
                }
            }
        }
        return imageUtils;
    }

    /**
     * 根据ID资源加载一张朴实无华的照片
     * @param context 场景
     * @param drawableId 资源的Id
     * @param imageView 照片对象
     */
    public static ImageUtils singleImage(Context context, int drawableId, ImageView imageView){
        Glide.with(context).load(drawableId).into(imageView);
        if (imageUtils==null){
            synchronized (ImageUtils.class){
                if (imageUtils==null){
                    imageUtils=new ImageUtils(context,drawableId,imageView,false);
                    return imageUtils;
                }
            }
        }
        return imageUtils;
    }

    /**
     * 加载动态图片
     */
    public void asGif(){
        if (flag){
            Glide.with(context).asGif().load(url).into(imageView);
        }else {
            Glide.with(context).asGif().load(drawableId).into(imageView);
        }
    }
    /**
     * 加载圆形图片
     */
    public void isCircle(){
        if (flag){
            Glide.with(context).load(url).circleCrop().into(imageView);
        }else {
            Glide.with(context).load(drawableId).circleCrop().into(imageView);
        }
    }

}
