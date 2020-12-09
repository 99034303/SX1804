package com.wmc.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.squareup.picasso.Picasso;

/**
 * @author 魏铭池
 */
public class ImageUtils {
    private static volatile ImageUtils imageUtils;

    public static final int GLIDE =1;
    public static final int PICASSO =2;
    public static final int FRESCO =3;
    private int mode;

    public ImageUtils(int util) {
        mode=util;
    }

    public static ImageUtils getInstance(int utils){
        if (imageUtils==null){
            synchronized (ImageUtils.class){
                if (imageUtils==null){
                    imageUtils=new ImageUtils(utils);
                    return imageUtils;
                }
            }
        }
        return imageUtils;
    }


    /**
     * 加载一张朴实无华的照片
     */
    public void single(Context context,String url,ImageView imageView){
        load(context,url,imageView,false,false);
    }
    public void glideGif(Context context, String url, ImageView imageView){
        load(context,url,imageView,false,true);
    }
    public void glideCircle(Context context,String url,ImageView imageView){
        load(context,url,imageView,true,false);
    }
    public void glideGifAndCircle(Context context,String url,ImageView imageView){
        load(context,url,imageView,true,true);
    }

    private void load(Context context,String url,ImageView imageView,boolean circle,boolean gif){
        switch (mode){
            case 1:
                if (circle&&!gif){
                    Glide.with(context).load(url).circleCrop().into(imageView);
                }else if (!circle&&gif){
                    Glide.with(context).asGif().load(url).into(imageView);
                }else if (circle&&gif){
                    Glide.with(context).asGif().load(url).circleCrop().into(imageView);
                }else {
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
}

