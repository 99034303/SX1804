package com.wmc.usercenter.model.api;

import com.example.net.BaseEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.RequestEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    /**
     * 获取请求添加好友数据
     * @param userid
     * @return
     */
    @GET("/gisim/friend/findReqFriends")
    Flowable<BaseEntity<List<FriendEntity>>> getRequestAddFriendData(@Query("userid")Integer userid);
}
