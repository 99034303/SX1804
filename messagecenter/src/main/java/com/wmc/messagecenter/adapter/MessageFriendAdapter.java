package com.wmc.messagecenter.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wmc.messagecenter.R;

import java.util.List;

public class MessageFriendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MessageFriendAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_friend_name,item);
    }
}
