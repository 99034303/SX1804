package com.wmc.usercenter.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bw.xmpplibrary.XmppManager;
import com.example.net.BaseEntity;
import com.example.net.RetrofitFactory;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.model.UserCenterModel;
import com.wmc.usercenter.model.api.HttpApi;

import java.util.List;

import io.reactivex.Observable;
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
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    XmppManager.getInstance().getXmppUserManager().login(loginBody.getPhonenumber(),loginBody.getPwd());
                                }
                            }).start();
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
     * 获取好友，搜索好友
     */
    @SuppressLint("CheckResult")
    @Override
    public void getFriend(String keyword) {
        mModel.getFriend(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<FriendEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity<List<FriendEntity>> friendEntityBaseEntity) {
                        mView.getFriend(friendEntityBaseEntity.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wt",e.getMessage());
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
                            if (booleanBaseEntity.getCode()==0){
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        XmppManager.getInstance().getXmppUserManager().createAccount(register.getPhonenumber(),register.getPwd());
//                                        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
//                                            @Override
//                                            public void Success(MsgEntity msgEntity) {
//                                                Log.i("===","成功");
//                                                mView.updateRegisterUI(booleanBaseEntity);
//                                            }
//
//                                            @Override
//                                            public void Failed(Throwable throwable) {
//                                                Log.i("===","失败");
//                                            }
//                                        });
//                                    }
//                                }).start();
                                mView.updateRegisterUI(booleanBaseEntity);
                            }
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void forgetCode() {
        mModel.forgetCode().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> forgetCodeEntityBaseEntity) throws Exception {
                        if (forgetCodeEntityBaseEntity.getCode()==0){
                            if (mView!=null) {
                                mView.ForgetCode(forgetCodeEntityBaseEntity.getData());
                            }
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void forgetChange(int id, String pwd) {
        mModel.forgetChange(id, pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseEntity<Boolean>>() {
                    @Override
                    public void accept(BaseEntity<Boolean> booleanBaseEntity) throws Exception {
                        if (booleanBaseEntity.getCode()==0){
                            if (mView!=null){
                                mView.ForgetChange(booleanBaseEntity.getData());
                            }
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
