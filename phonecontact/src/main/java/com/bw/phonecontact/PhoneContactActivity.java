package com.bw.phonecontact;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bw.phonecontact.entity.ContactEntity;
import com.bw.phonecontact.entity.MyEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvp.view.BaseActivity;
import com.example.view.ContactView;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.bw.phonecontact.adapter.ContactAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneContactActivity extends BaseActivity {
    private ImageView ivPhoneContactBackView;
    private ContactView contactView;
    //手机联系人数据源
    private List<ContactEntity> contactList = new ArrayList<>();
    private List<MyEntity> datas = new ArrayList<>();
    //手机联系人适配器
    private ContactAdapter adapter;
    private RecyclerView recyclerView;



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
        //查询联系人
        Cursor cursor = contentResolver.query(contactUri, null, null, null, null);
        if (cursor != null){
            //开始搜索联系人
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));//姓名
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));//手机号
                contactList.add(new ContactEntity(name,phoneNumber));
            }
            //关闭游标
            cursor.close();
        }

        adapter = new ContactAdapter(R.layout.contact_view2,R.layout.contact_view1,datas);

        //排序
        Collections.sort(contactList);

        for (int i = 0; i < contactList.size(); i++) {
            //第一个肯定是标题布局
            if (i == 0){
                try {
                    //获取第一个字的首字母
                    String shortPinyin = PinyinHelper.getShortPinyin(contactList.get(i).getName().substring(0, 1));
                    char c = shortPinyin.charAt(0);
                    //转换成大写字母
                    String s = String.valueOf((char)(c - 32));
                    //添加到数据源
                    datas.add(new MyEntity(true,s.toUpperCase()+""));//标题布局
                    datas.add(new MyEntity(contactList.get(i)));//添加到正常布局
                } catch (PinyinException e) {
                    e.printStackTrace();
                }
                continue;
            }

            if (!contactList.get(i).equals(contactList.get(i-1))){
                try {
                    //获取第一个字的首字母
                    String shortPinyin = PinyinHelper.getShortPinyin(contactList.get(i).getName().substring(0, 1));
                    //获取上一个数据的第一个字的首字母
                    String shortPinyin2 = PinyinHelper.getShortPinyin(contactList.get(i-1).getName().substring(0, 1));
                    //比较当前首个字是否与上一个数据的首个字是否一样
                    if (shortPinyin.equals(shortPinyin2)) {
                        //一样就添加到标题下的布局
                        datas.add(new MyEntity(contactList.get(i)));//正常布局
                    }else {
                        //如果当前首个字与上个字不一样添加到新的标题并在标题下添加上当前数据
                        char c = shortPinyin.charAt(0);
                        //转换成大写字母
                        String s = String.valueOf((char)(c - 32));

                        datas.add(new MyEntity(true,s.toUpperCase()+""));//添加到标题布局
                        datas.add(new MyEntity(contactList.get(i)));//添加到正常布局
                    }
                } catch (PinyinException e) {
                    e.printStackTrace();
                }

            }
        }

        //添加适配器
        contactView.setContactAdapter(adapter,this);

        //获取联系人布局
        recyclerView = contactView.getRecycler();

        //联系人列表的点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyEntity myEntity = (MyEntity) adapter.getItem(position);
                if (myEntity.header != null){//判断是否点击到标题
                    return;
                }

                Toast.makeText(PhoneContactActivity.this, ""+myEntity.t.getName(), Toast.LENGTH_SHORT).show();
            }
        });



        //添加联系人按钮+号的事件
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_contact_add2);
                TextView textView2 = (TextView) adapter.getViewByPosition(recyclerView,position, R.id.tv_contact_wait);

                if (view.getId() == R.id.iv_contact_add2){
                    Toast.makeText(PhoneContactActivity.this, "已添加好友等待好友验证", Toast.LENGTH_SHORT).show();
                    imageView.setVisibility(View.GONE);//隐藏加号
                    textView2.setVisibility(View.VISIBLE);//显示等待验证
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_contract;
    }

}