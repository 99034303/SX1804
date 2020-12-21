package com.wmc.usercenter.contract;

import com.example.mvp.model.IModel;
import com.example.mvp.presenter.BasePresenter;
import com.example.mvp.view.IView;
import com.example.net.BaseEntity;
import com.wmc.usercenter.entity.AddEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface Contract {
    interface View extends IView {
        void updateLoginUI(BaseEntity<LoginEntity> baseEntity);
        void updateRegisterUI(BaseEntity<Boolean> baseEntity);
        void ForgetCode(String code);
        void ForgetChange(boolean flag);
<<<<<<< HEAD
        void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result);
=======
        void getFriend(List<FriendEntity> friendEntity);
        void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result);
        void AddFriend(BaseEntity<Boolean> baseEntity);
>>>>>>> 9ab9c89bbe41f562f344ef9dddf68a82917ac0ff

    }

    interface Model extends IModel {
        //注册
        Observable<BaseEntity<Boolean>> register(RequestEntity registerBody);

        //登录
        Observable<BaseEntity<LoginEntity>> login(RequestEntity loginBody);

        //验证码
        Observable<BaseEntity<String>> forgetCode();

        //修改密码
        Observable<BaseEntity<Boolean>> forgetChange(int id,String pwd);

<<<<<<< HEAD
=======
        //获取好友
        Observable<BaseEntity<List<FriendEntity>>> getFriend(String keyword);

        //添加好友
        Observable<BaseEntity<Boolean>> AddFriend(AddEntity addEntity);

>>>>>>> 9ab9c89bbe41f562f344ef9dddf68a82917ac0ff
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

=======
>>>>>>> 9ab9c89bbe41f562f344ef9dddf68a82917ac0ff
        /**
         * 获取验证码
         */
        public abstract void forgetCode();

        public abstract void forgetChange(int id,String pwd);

<<<<<<< HEAD
        public abstract void forgetChange(int id,String pwd);

=======
>>>>>>> 9ab9c89bbe41f562f344ef9dddf68a82917ac0ff

        /**
         * 获取请求添加好友数据
         * @param userid
         */
        public abstract void getRequestAddFriendData(Integer userid);
    }
}
