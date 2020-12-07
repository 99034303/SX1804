package com.example.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvp.presenter.BasePresenter;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IFragment {
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
        createPresenter();
        initView();
        initData();
    }

    protected abstract void createPresenter();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }


}
