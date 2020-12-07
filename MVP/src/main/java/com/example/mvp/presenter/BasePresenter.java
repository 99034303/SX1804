package com.example.mvp.presenter;

import com.example.mvp.model.IModel;
import com.example.mvp.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
    protected M mModel;
    protected V mView;



}
