package com.wmc.usercenter.contract;

import com.example.mvp.model.IModel;
import com.example.mvp.presenter.BasePresenter;
import com.example.mvp.view.IView;
import com.example.net.BaseEntity;
import com.wmc.usercenter.entity.AddEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.RequestEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface Contract {
    interface View extends IView {
        void updateLoginUI(BaseEntity<LoginEntity> baseEntity);
        void updateRegisterUI(BaseEntity<Boolean> baseEntity);
<<<<<<< HEAD
        void ForgetCode(String code);
        void ForgetChange(boolean flag);
        void getFriend(List<FriendEntity> friendEntity);
        void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result);
        void AddFriend(BaseEntity<Boolean> baseEntity);
=======
        void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result);
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
    }

    interface Model extends IModel {
        //注册
        Observable<BaseEntity<Boolean>> register(RequestEntity registerBody);

        //登录
        Observable<BaseEntity<LoginEntity>> login(RequestEntity loginBody);

<<<<<<< HEAD
        //验证码
        Observable<BaseEntity<String>> forgetCode();

        //修改密码
        Observable<BaseEntity<Boolean>> forgetChange(int id,String pwd);

        //获取好友
        Observable<BaseEntity<List<FriendEntity>>> getFriend(String keyword);

        //添加好友
        Observable<BaseEntity<Boolean>> AddFriend(AddEntity addEntity);

=======
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
        //获取请求添加好友数据
        Flowable<BaseEntity<List<FriendEntity>>> getRequestAddFriendData(Integer userid);
    }

    abstract class Presenter extends BasePresenter<Model,View> {

        public Presenter(View mView) {
            super(mView);
        }

        /**
         * 登录
         * @param loginBody
         */
        public abstract void login(RequestEntity loginBody);

        /**
         * 获取好友
         */
        public abstract void getFriend(String keyword);

        /**
         * 添加好友
         */
        public abstract void AddFriend(AddEntity addEntity);

        /**
         * 注册
         * @param registerBody
         */
        public abstract void register(RequestEntity registerBody);

<<<<<<< HEAD

        /**
         * 获取验证码
         */
        public abstract void forgetCode();

        public abstract void forgetChange(int id,String pwd);


=======
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
        /**
         * 获取请求添加好友数据
         * @param userid
         */
        public abstract void getRequestAddFriendData(Integer userid);
    }
}
