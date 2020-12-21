package com.wmc.messagecenter.mvp.model.protocol

import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("/gisim/msg/sendMsg")
    fun sendSingleMessage(singleEntity:SingleEntity):Flowable<Entity<SingleEntity>>

    @POST("/gisim/msg/receiveMsg")
    fun getSingleMessage(singleEntity:SingleEntity):Flowable<Entity<SingleEntity>>

}