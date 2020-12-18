package com.wmc.usercenter.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.wmc.usercenter.R;
import com.wmc.usercenter.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseMultiItemQuickAdapter<ContactEntity, BaseViewHolder> {
    private List<ContactEntity> contactEntities = new ArrayList<>();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ContactAdapter(List<ContactEntity> data) {
        super(data);
        contactEntities.addAll(data);
        addItemType(0,R.layout.contact_view1);
        addItemType(1,R.layout.contact_view2);
    }

    @Override
    protected int getDefItemViewType(int position) {
        Log.d("zqy",position+"");
        if (position == 0){
            return 0;
        }else {
            //获取名字
            ContactEntity contactEntity = contactEntities.get(position);
            String name = contactEntity.getName();
            //获取名字的第一个字
            String first_ = name.substring(0,1);
            try {
                String shortPinyin = PinyinHelper.getShortPinyin(first_);
                //然后获取上一条数据的第一个字的拼音
                String last_shortPinyin = PinyinHelper.getShortPinyin(contactEntities.get(position - 1).getName().substring(0,1));

                if (shortPinyin.equals(last_shortPinyin)) {
                    return 1;
                }
            } catch (PinyinException e) {
                e.printStackTrace();
            }

            return 0;
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactEntity item) {
        switch (helper.getItemViewType()){
            case 0:
                helper.setText(R.id.tv_contact_name,item.getName());

                break;
            case 1:
                helper.setText(R.id.tv_contact_name2,item.getName());
                break;
        }
    }
}
