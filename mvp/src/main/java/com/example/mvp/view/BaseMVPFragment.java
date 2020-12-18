package com.example.mvp.view;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvp.presenter.BasePresenter;

public abstract class BaseMVPFragment<P extends BasePresenter> extends Fragment implements MyFindViewById {
    protected P mPresenter;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView = inflater.inflate(getLayoutId(), container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    "android.permission.CAMERA",
                    "android.permission.VIBRATE",
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_NETWORK_STATE",
                    "android.permission.ACCESS_WIFI_STATE",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_FINE_LOCATION"

            }, 100);
        }
        createPresenter();
        bindView();
        initView();
        initData();
    }

    protected abstract void bindView();

    protected abstract void createPresenter();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);

    }


}
