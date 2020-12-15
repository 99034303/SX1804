package com.wmc.usercenter.model.api;

import com.example.net.BaseEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {

    /**
     * 注册
     * @param registerBody
     * @return
     */
    @POST("/gisim/user/register")
    Observable<BaseEntity<Boolean>> register(@Body RequestEntity registerBody);


    /**
     * 登录
     * @param loginBody
     * @return
     */
    @POST("/gisim/user/login")
    Observable<BaseEntity<LoginEntity>> login(@Body RequestEntity loginBody);
}