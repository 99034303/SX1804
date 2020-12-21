package com.wmc.usercenter.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.mvp.view.BaseMVPFragment;
import com.example.net.BaseEntity;
import com.wmc.usercenter.R;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

import java.text.Collator;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ContactsListFragment extends BaseMVPFragment<UserCenterPresenter> implements Contract.View {

    @Override
    protected void bindView() {

    }

    @Override
    protected void createPresenter() {
        mPresenter=new UserCenterPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_usercenter_contacts_contactslist;
    }

    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {

    }

    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {

    }

    @Override
<<<<<<< HEAD
    public void ForgetCode(String code) {

    }

    @Override
    public void ForgetChange(boolean flag) {

    }

    @Override
    public void getFriend(List<FriendEntity> friendEntity) {

    }

    @Override
    public void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result) {

    }

    @Override
    public void AddFriend(BaseEntity<Boolean> baseEntity) {

    }
=======
    public void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result) {

    }
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
}
