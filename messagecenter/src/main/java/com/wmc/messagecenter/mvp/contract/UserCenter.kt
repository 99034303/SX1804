package com.wmc.messagecenter.mvp.contract

import com.example.mvp.model.IModel
import com.example.mvp.presenter.BasePresenter
import com.example.mvp.view.IView
import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import io.reactivex.Flowable
import org.jivesoftware.smack.chat2.Chat
import org.jivesoftware.smackx.muc.MultiUserChat
import java.util.HashMap

interface UserCenter {
    abstract interface UserCenterView : IView{

    }
    abstract class UserCenterModel : IModel{
        abstract fun sendSingleMessage(singleEntity: SingleEntity):Flowable<Entity<Boolean>>
        abstract fun getSingleMessage(singleEntity: SingleEntity):Flowable<Entity<Boolean>>
    }

    abstract class UserCenterPresenter(mView: UserCenterView) : BasePresenter<UserCenterModel, UserCenterView>(mView){
        abstract fun sendSingleMessage(chat: Chat,context: String,fromuser:Int,msgtypeid:Int,touser:Int): Flowable<Entity<Boolean>>
        abstract fun getSingleMessage(): Flowable<Entity<Boolean>>
        abstract fun sendMessage(chat: Chat, muc: MultiUserChat, message:String)
        abstract fun getHisMessage(): MutableMap<String, MutableList<HashMap<String, String>>>?

        abstract fun sendVideo(user: String, filePath: String)
    }

}