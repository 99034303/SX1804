package com.wmc.usercenter.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import com.bw.xmpplibrary.XmppManager;
import com.example.net.BaseEntity;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestAddFriendsResponseEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.model.UserCenterModel;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
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

    /**
     * 获取请求添加好友数据
     * @param userid
     */
    @Override
    public void getRequestAddFriendData(Intent userid) {
        mModel.getRequestAddFriendData(userid).subscribe(new FlowableSubscriber<BaseEntity<List<RequestAddFriendsResponseEntity>>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(BaseEntity<List<RequestAddFriendsResponseEntity>> requestAddFriendsResponseEntityBaseEntity) {
                if(requestAddFriendsResponseEntityBaseEntity.getCode()!=-1){
                    mView.updateRequestAddFriendUI(requestAddFriendsResponseEntityBaseEntity);
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

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
