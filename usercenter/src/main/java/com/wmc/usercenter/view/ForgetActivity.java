package com.wmc.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wmc.imageloader.ImageUtils;
import com.wmc.usercenter.R;
@Route(path = "/view/ForgetActivity")
public class ForgetActivity extends AppCompatActivity {
    private RelativeLayout forgetParent;
    private ImageView forgetBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        forgetParent = findViewById(R.id.forget_parent);
        forgetBackground = findViewById(R.id.forget_background);

        ImageUtils.getInstance(ImageUtils.GLIDE).glideGif(this,"https://c-ssl.duitang.com/uploads/item/202002/17/20200217164048_AUwJP.thumb.1000_0.gif",forgetBackground);

    }
}