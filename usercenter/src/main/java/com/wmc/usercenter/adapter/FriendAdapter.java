package com.wmc.usercenter.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wmc.usercenter.R;
import com.wmc.usercenter.entity.FriendEntity;

import java.util.List;

public class FriendAdapter extends BaseQuickAdapter<FriendEntity, BaseViewHolder> {
    public FriendAdapter(int layoutResId, @Nullable List<FriendEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendEntity item) {
        helper.setText(R.id.friend_name,item.getPhonenumber());
        helper.addOnClickListener(R.id.friend_add);
    }
}
