package com.example.home.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.home.R;
import com.wmc.imageloader.ImageUtils;

import java.util.List;

public class Friend_GroupAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public Friend_GroupAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        ImageView view = helper.getView(R.id.iv_friend);
        if (view!=null){
            Glide.with(mContext).load(item).circleCrop().into(view);
        }
//        ImageUtils.getInstance(ImageUtils.).glideCircle(mContext,R.drawable.ic_friend_group,view);
    }
}
