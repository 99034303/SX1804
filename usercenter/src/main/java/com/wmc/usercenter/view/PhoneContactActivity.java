package com.wmc.usercenter.view;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.view.BaseActivity;
import com.example.view.ContactView;
import com.wmc.usercenter.R;
import com.wmc.usercenter.adapter.ContactAdapter;
import com.wmc.usercenter.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

import de.measite.minidns.record.A;

public class PhoneContactActivity extends BaseActivity {

    private ImageView ivPhoneContactBackView;
    private ContactView contactView;
    //手机联系人数据源
    private List<ContactEntity> contactList = new ArrayList<>();
    //手机联系人适配器
    private ContactAdapter adapter;



    @Override
    protected void bindView() {
        ivPhoneContactBackView = (ImageView) findViewById(R.id.iv_phone_contact_back_view);
        contactView = (ContactView) findViewById(R.id.contact_view);
    }

    @Override
    protected void initData() {
        //关闭当前页面
        ivPhoneContactBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initView() {
        //获取内容提供者
        ContentResolver contentResolver = getContentResolver();
        //转换练习人的Uri
        Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = contentResolver.query(contactUri, null, null, null, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));//姓名
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));//手机号
                contactList.add(new ContactEntity(name,phoneNumber));
            }

            cursor.close();
        }

        adapter = new ContactAdapter(contactList);
        contactView.setContactAdapter(adapter,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_contract;
    }

}