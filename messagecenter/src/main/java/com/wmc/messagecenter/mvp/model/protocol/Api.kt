package com.wmc.messagecenter.mvp.model.protocol

import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("/gisim/msg/sendMsg")
    fun sendSingleMessage(@Body singleEntity:SingleEntity):Flowable<Entity<Boolean>>

    @POST("/gisim/msg/receiveMsg")
    fun getSingleMessage(@Body singleEntity:SingleEntity):Flowable<Entity<Boolean>>

}