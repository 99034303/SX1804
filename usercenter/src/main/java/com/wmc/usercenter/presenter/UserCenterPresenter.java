package com.wmc.usercenter.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.bw.commonlibrary.LogUtils;
import com.bw.xmpplibrary.XmppManager;
import com.bw.xmpplibrary.callback.IMsgCallback;
import com.bw.xmpplibrary.entity.MsgEntity;
import com.example.net.BaseEntity;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.FriendEntity;
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
    private Handler handler = new Handler();

    public UserCenterPresenter(Contract.View mView) {
        super(mView);
    }


    @Override
    protected void createModel() {
        mModel = UserCenterModel.getInstance();
    }


    /**
     * 登录
     *
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
                        if (mView != null) {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (XmppManager.getInstance().getXmppUserManager().login(loginBody.getPhonenumber(), loginBody.getPwd())) {
//                                        handler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                mView.updateLoginUI(loginEntityBaseEntity);
//                                            }
//                                        });
//                                    }
//                                }
//                            }).start();
                            mView.updateLoginUI(loginEntityBaseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("zqy", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 注册
     *
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
                        if (mView != null) {
                            if (booleanBaseEntity.getCode() == 0) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        XmppManager.getInstance().getXmppUserManager().createAccount(register.getPhonenumber(), register.getPwd());
                                    }
                                }).start();
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
                        if (forgetCodeEntityBaseEntity.getCode() == 0) {
                            if (mView != null) {
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
                        if (booleanBaseEntity.getCode() == 0) {
                            if (mView != null) {
                                mView.ForgetChange(booleanBaseEntity.getData());
                            }
                        }
                    }
                });
    }

    /**
     * 获取请求添加好友数据
     *
     * @param userid
     */
    @Override
    public void getRequestAddFriendData(Integer userid) {
        mModel.getRequestAddFriendData(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<BaseEntity<List<FriendEntity>>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseEntity<List<FriendEntity>> requestAddFriendsResponseEntityBaseEntity) {
                        if (requestAddFriendsResponseEntityBaseEntity.getCode() != -1) {
                            mView.updateRequestAddFriendUI(requestAddFriendsResponseEntityBaseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.i("" + t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }


    /**
     * 销毁 P层
     */
    public void DestroyPresenter() {
        if (mModel != null) {
            mModel = null;
        }
        if (mView != null) {
            mView = null;
        }
    }
}
