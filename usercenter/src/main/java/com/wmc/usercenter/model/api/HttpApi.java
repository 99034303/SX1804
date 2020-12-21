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
<<<<<<< HEAD
import retrofit2.http.PUT;
=======

import retrofit2.http.PUT;

>>>>>>> a56e93eb5630da1bf520a375fc091e9388b91c27
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
     * 获取验证码
     * @return
     */
    @GET("/gisim/user/verificationCode")
    Observable<BaseEntity<String>> code(@Query(value = "phoneNumber") String phoneNumber);

    @PUT("/gisim/user/modifyPwd")
    Observable<BaseEntity<Boolean>> Change(@Query("id") int id,@Query(value = "pwd") String pwd);

<<<<<<< HEAD

     /**
     * 获取请求添加好友数据
     * @param userid
     * @return
     */
    @GET("/gisim/friend/findReqFriends")
    Flowable<BaseEntity<List<FriendEntity>>> getRequestAddFriendData(@Query("userid")Integer userid);
=======
    @GET("/gisim/friend/findReqFriends")
    Flowable<BaseEntity<List<FriendEntity>>> getRequestAddFriendData(@Query("userid")Integer userid);

>>>>>>> a56e93eb5630da1bf520a375fc091e9388b91c27
}
