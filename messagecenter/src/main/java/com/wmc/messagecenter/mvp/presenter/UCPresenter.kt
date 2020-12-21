package com.wmc.messagecenter.mvp.presenter

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.mvp.presenter.BasePresenter
import com.example.mvp.view.IView
import com.wmc.messagecenter.mvp.contract.UserCenter
import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import com.wmc.messagecenter.mvp.model.service.UCModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UCPresenter(mView: UserCenter.UserCenterView) : UserCenter.UserCenterPresenter(mView) {

    @SuppressLint("CheckResult")
    override fun sendSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>> {
        return mModel.sendSingleMessage(singleEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun getSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>> {
        return mModel.getSingleMessage(singleEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    override fun createModel() {
        mModel=UCModel()
    }
}