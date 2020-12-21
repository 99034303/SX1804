package com.wmc.messagecenter.mvp.model.service

import com.example.net.RetrofitFactory
import com.wmc.messagecenter.mvp.contract.UserCenter
import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import com.wmc.messagecenter.mvp.model.protocol.Api
import io.reactivex.Flowable

class UCModel: UserCenter.UserCenterModel() {
    override fun sendSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>> {
        return RetrofitFactory.getInstance().create(Api::class.java).sendSingleMessage(singleEntity)
    }

    override fun getSingleMessage(singleEntity: SingleEntity): Flowable<Entity<SingleEntity>> {
        return RetrofitFactory.getInstance().create(Api::class.java).getSingleMessage(singleEntity)
    }
}