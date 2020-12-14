package com.wmc.usercenter.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.net.BaseEntity;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.model.UserCenterModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserCenterPresenter extends Contract.Presenter {

    public UserCenterPresenter(Contract.View mView) {
        super(mView);
    }


    @Override
    protected void createModel() {
        mModel = UserCenterModel.getInstance();
    }


    /**
     * 登录
     * @param loginBody
     */
    @SuppressLint("CheckResult")
    @Override
    public void login(final RequestEntity loginBody) {
        mModel.login(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<LoginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<LoginEntity> loginEntityBaseEntity) {
                        if (mView != null){
                            mView.updateLoginUI(loginEntityBaseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("zqy",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 注册
     * @param register
     */
    @SuppressLint("CheckResult")
    @Override
    public void register(RequestEntity register) {
        mModel.register(register)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<Boolean>>() {
                    @Override
                    public void accept(BaseEntity<Boolean> booleanBaseEntity) throws Exception {
                        if (mView != null){
                            mView.updateRegisterUI(booleanBaseEntity);
                        }
                    }
                });
    }


    /**
     * 销毁 P层
     */
    public void DestroyPresenter(){
        if (mModel != null){
            mModel = null;
        }
        if (mView != null){
            mView = null;
        }
    }
}
