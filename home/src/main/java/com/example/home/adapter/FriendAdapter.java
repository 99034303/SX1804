package com.example.home.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.home.R;
import com.wmc.imageloader.ImageUtils;

import java.util.List;

public class FriendAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public FriendAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        ImageView view = helper.getView(R.id.iv_friend);
        if (view!=null){
            Glide.with(mContext).load(item).circleCrop().into(view);
        }
//        Glide.with(mContext).load(R.drawable.ic_friend).into(view);
//        ImageUtils.getInstance(1).glideCircle(mContext,R.drawable.ic_friend,view);
    }
}
