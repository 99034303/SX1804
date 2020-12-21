package com.wmc.messagecenter.mvp.contract

import com.example.mvp.model.IModel
import com.example.mvp.presenter.BasePresenter
import com.example.mvp.view.IView
import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import io.reactivex.Flowable

interface UserCenter {
    abstract interface UserCenterView : IView{

    }
    abstract class UserCenterModel : IModel{
        abstract fun sendSingleMessage(singleEntity: SingleEntity):Flowable<Entity<SingleEntity>>
        abstract fun getSingleMessage(singleEntity: SingleEntity):Flowable<Entity<SingleEntity>>
    }

    abstract class UserCenterPresenter(mView: UserCenterView) : BasePresenter<UserCenterModel, UserCenterView>(mView){
        abstract fun sendSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>>
        abstract fun getSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>>
    }

}