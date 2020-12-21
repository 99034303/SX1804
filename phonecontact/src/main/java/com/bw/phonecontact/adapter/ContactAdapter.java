package com.bw.phonecontact.adapter;

import com.bw.phonecontact.R;
import com.bw.phonecontact.entity.MyEntity;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ContactAdapter extends BaseSectionQuickAdapter<MyEntity, BaseViewHolder> {

    public ContactAdapter(int layoutResId, int sectionHeadResId, List<MyEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MyEntity item) {
        helper.setText(R.id.tv_contact_letter,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyEntity item) {
        helper.setText(R.id.tv_contact_name2,item.t.getName());

        helper.addOnClickListener(R.id.iv_contact_add2);
    }
}
