package com.example.mvp.presenter;

import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;


//p层抽象类
public abstract class BasePresenter<M extends IModel,V extends IView> {
    protected M mModel;
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
        createModel();
    }

    protected abstract void createModel();
}
