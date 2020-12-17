package com.wmc.usercenter.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wmc.imageloader.ImageUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.entity.RequestAddFriendsResponseEntity;

import java.util.List;

public class RequestAddFriendListAdapter extends BaseQuickAdapter<RequestAddFriendsResponseEntity, BaseViewHolder> {
    public RequestAddFriendListAdapter(int layoutResId, @Nullable List<RequestAddFriendsResponseEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RequestAddFriendsResponseEntity item) {
        ImageUtils.getInstance(1).glideCircle(mContext,item.getHeadimg(),helper.getView(R.id.img_adapter_userCenter_contacts_requestAddFriendList_head));
        helper.setText(R.id.tv_adapter_userCenter_contacts_requestAddFriendList_nickName,item.getNick());
    }
}
