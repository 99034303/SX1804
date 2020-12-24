package com.wmc.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;

/**
 * @author 魏铭池
 */
public class ImageUtils {
    private static volatile ImageUtils imageUtils;

    public static final int GLIDE = 1;
    public static final int PICASSO = 2;
    public static final int FRESCO = 3;
    private int mode;

    public ImageUtils(int util) {
        mode = util;
    }

    public static ImageUtils getInstance(int utils) {
        if (imageUtils == null) {
            synchronized (ImageUtils.class) {
                if (imageUtils == null) {
                    imageUtils = new ImageUtils(utils);
                    return imageUtils;
                }
            }
        }
        return imageUtils;
    }

    /**
     * 加载一张朴实无华的照片
     */
    public void single(Context context, String url, ImageView imageView) {
        load(context, url, imageView, false, false);
    }

    public void glideGif(Context context, String url, ImageView imageView) {
        load(context, url, imageView, false, true);
    }

    public void glideCircle(Context context, String url, ImageView imageView) {
        load(context, url, imageView, true, false);
    }

    public void glideGifAndCircle(Context context, int drawable, ImageView imageView) {
        load(context, drawable, imageView, true, true);
    }

    private void load(Context context, String url, ImageView imageView, boolean circle, boolean gif) {
        switch (mode) {
            case 1:
                if (circle && !gif) {
                    Glide.with(context).load(url).circleCrop().into(imageView);
                } else if (!circle && gif) {
                    Glide.with(context).asGif().load(url).into(imageView);
                } else if (circle && gif) {
                    Glide.with(context).asGif().load(url).circleCrop().into(imageView);
                } else {
                    Glide.with(context).load(url).into(imageView);
                }
                break;
            case 2:
                Picasso.with(context).load(url).into(imageView);
                break;
            default:
                break;
        }
    }

    public void single(Context context, int drawable, ImageView imageView) {
        load(context, drawable, imageView, false, false);
    }

    public void glideGif(Context context, int drawable, ImageView imageView) {
        load(context, drawable, imageView, false, true);
    }

    public void glideCircle(Context context, int drawable, ImageView imageView) {
        load(context, drawable, imageView, true, false);
    }

    public void glideGifAndCircle(Context context, String url, ImageView imageView) {
        load(context, url, imageView, true, true);
    }

    private void load(Context context, int drawable, ImageView imageView, boolean circle, boolean gif) {
        switch (mode) {
            case 1:
                if (circle && !gif) {
                    Glide.with(context).load(drawable).circleCrop().into(imageView);
                } else if (!circle && gif) {
                    Glide.with(context).asGif().load(drawable).into(imageView);
                } else if (circle && gif) {
                    Glide.with(context).asGif().load(drawable).circleCrop().into(imageView);
                } else {
                    Glide.with(context).load(drawable).into(imageView);
                }
                break;
            case 2:
                Picasso.with(context).load(drawable).into(imageView);
                break;
            default:
                break;
        }
    }
        /**
         * 图片二次采样
         * @param path 图片的路径
         * @param scaleSize 缩放比例
         * @return
         */
        public Bitmap scaleImageByPath (String path,int scaleSize){
            //设置缩放尺寸
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = scaleSize;
            return BitmapFactory.decodeFile(path, options);
        }

        /**
         * 图片二次采样重载
         * @param id 图片的资源id
         * @param scaleSize 缩放比例
         * @return
         */
        public Bitmap scaleImageById (Context context,int id, int scaleSize){
            //设置缩放尺寸
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = scaleSize;
            return BitmapFactory.decodeResource(context.getResources(), id, options);
        }

        /**
         * 图片二次采样重载
         * @param stream 流
         * @param scaleSize 缩放比例
         * @return
         */
        public Bitmap scaleImageByStream (InputStream stream,int scaleSize){
            //设置缩放尺寸
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = scaleSize;
            return BitmapFactory.decodeStream(stream, null, options);

        }

}

