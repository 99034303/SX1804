package com.wmc.usercenter.model;

import com.example.net.BaseEntity;
import com.example.net.RetrofitFactory;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.AddEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.model.api.HttpApi;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Query;

public class UserCenterModel implements Contract.Model {
    /**
     * 单例，防止内存泄漏
     */
    private static UserCenterModel userCenterModel;

    public static UserCenterModel getInstance(){
        if (userCenterModel == null){
            userCenterModel = new UserCenterModel();
        }
        return userCenterModel;
    }


    /**
     * 注册
     * @param registerBody
     * @return
     */
    @Override
    public Observable<BaseEntity<Boolean>> register(RequestEntity registerBody) {
        return RetrofitFactory.getInstance().create(HttpApi.class).register(registerBody);
    }


    /**
     * 登录
     * @param loginBody
     * @return
     */
    @Override
    public Observable<BaseEntity<LoginEntity>> login(RequestEntity loginBody) {
        Observable<BaseEntity<LoginEntity>> observable = RetrofitFactory.getInstance().create(HttpApi.class).login(loginBody);

        return observable;
    }

    @Override
    public Observable<BaseEntity<String>> forgetCode() {
        return RetrofitFactory.getInstance().create(HttpApi.class).code("1");
    }

    @Override
    public Observable<BaseEntity<Boolean>> forgetChange(int id,String pwd) {





        return RetrofitFactory.getInstance().create(HttpApi.class).Change(id, pwd);
    }

    /**
     * 获取好友，搜索好友
     */
    @Override
    public Observable<BaseEntity<List<FriendEntity>>> getFriend(String keyword) {
        return RetrofitFactory.getInstance().create(HttpApi.class).getFriend(keyword);
    }

    /**
     * 添加好友
     * @param addEntity
     * @return
     */
    @Override
    public Observable<BaseEntity<Boolean>> AddFriend(AddEntity addEntity) {
        return RetrofitFactory.getInstance().create(HttpApi.class).AddFriend(addEntity);
    }


    /**
 * 获取请求添加好友数据
 * @param userid
 * @return
 */
        @Override
        public Flowable<BaseEntity<List<FriendEntity>>> getRequestAddFriendData(Integer userid) {
            return RetrofitFactory.getInstance().create(HttpApi.class).getRequestAddFriendData(userid);
        }
}
