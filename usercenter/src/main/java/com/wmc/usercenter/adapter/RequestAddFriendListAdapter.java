package com.wmc.usercenter.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wmc.imageloader.ImageUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.entity.FriendEntity;

import java.util.List;

public class RequestAddFriendListAdapter extends BaseQuickAdapter<FriendEntity, BaseViewHolder> {
    public RequestAddFriendListAdapter(int layoutResId, @Nullable List<FriendEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendEntity item) {
<<<<<<< HEAD
        ImageUtils.getInstance(ImageUtils.GLIDE).glideCircle(mContext,item.getHeadimg().toString(),helper.getView(R.id.img_adapter_userCenter_contacts_requestAddFriendList_head));
        helper.setText(R.id.tv_adapter_userCenter_contacts_requestAddFriendList_nickName, (Integer) item.getNick());
=======
        ImageUtils.getInstance(ImageUtils.GLIDE).glideCircle(mContext,item.getHeadimg(),helper.getView(R.id.img_adapter_userCenter_contacts_requestAddFriendList_head));
        helper.setText(R.id.tv_adapter_userCenter_contacts_requestAddFriendList_nickName,item.getNick());
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
        helper.addOnClickListener(R.id.tv_adapter_userCenter_contacts_requestAddFriendList_agreement);
    }
}
