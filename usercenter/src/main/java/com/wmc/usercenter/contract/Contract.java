package com.wmc.usercenter.contract;

import com.example.mvp.model.IModel;
import com.example.mvp.presenter.BasePresenter;
import com.example.mvp.view.IView;
import com.example.net.BaseEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;

import io.reactivex.Observable;

public interface Contract {
    interface View extends IView {
        void updateLoginUI(BaseEntity<LoginEntity> baseEntity);
        void updateRegisterUI(BaseEntity<Boolean> baseEntity);
    }

    interface Model extends IModel {
        //注册
        Observable<BaseEntity<Boolean>> register(RequestEntity registerBody);

        //登录
        Observable<BaseEntity<LoginEntity>> login(RequestEntity loginBody);
    }

    abstract class Presenter extends BasePresenter<Model,View> {

        public Presenter(View mView) {
            super(mView);
        }

        /**
         * 登录
         * @param loginBody
         */
        public abstract void login(RequestEntity loginBody);


        /**
         * 注册
         * @param registerBody
         */
        public abstract void register(RequestEntity registerBody);
    }
}
