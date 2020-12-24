package com.wmc.messagecenter.mvp.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.*
import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import com.bw.xmpplibrary.XmppManager
import com.bw.xmpplibrary.utils.BitmapUtils
import com.example.mvp.presenter.BasePresenter
import com.example.mvp.view.IView
import com.wmc.messagecenter.mvp.contract.UserCenter
import com.wmc.messagecenter.mvp.model.entity.Entity
import com.wmc.messagecenter.mvp.model.entity.SingleEntity
import com.wmc.messagecenter.mvp.model.service.UCModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jivesoftware.smack.chat2.Chat
import org.jivesoftware.smackx.muc.MultiUserChat
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class UCPresenter(mView: UserCenter.UserCenterView) : UserCenter.UserCenterPresenter(mView) {
    lateinit var singleEntity: SingleEntity
    @SuppressLint("CheckResult")
    override fun sendSingleMessage(chat: Chat,context: String,fromuser:Int,msgtypeid:Int,touser:Int): Flowable<Entity<Boolean>> {
        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat,context);
        return mModel.sendSingleMessage(singleEntity=SingleEntity(SystemClock.currentThreadTimeMillis().toString(),context,fromuser,0,0,msgtypeid, touser)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun getSingleMessage(): Flowable<Entity<Boolean>> {
        return mModel.getSingleMessage(singleEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    override fun sendMessage(chat: Chat,muc: MultiUserChat,message:String) {
        XmppManager.getInstance().xmppMsgManager.sendMessage(chat, muc, message)
    }

    override fun getHisMessage(): MutableMap<String, MutableList<HashMap<String, String>>>? {
        return XmppManager.getInstance().xmppMsgManager.hisMessage
    }

    override fun sendImage(user:String,o: Any) {
        var bitmap:Bitmap
        when(o){
            o is String->{
                 bitmap = decodeFile(o as String?)
            }
            o is Int->{
                 bitmap = decodeResource(Resources.getSystem(), o as Int)
            }
            o is ByteArray->{
                 bitmap= decodeByteArray(o as ByteArray?, 0, o.size)
            }
            o is InputStream->{
                 bitmap = decodeStream(o as InputStream?)
            }
        }



    }

    override fun sendVideo(user:String,filePath:String) {
        XmppManager.getInstance().xmppMsgManager.sendFile(user, filePath)
    }

    override fun createModel() {
        mModel=UCModel()
    }
}