package com.wmc.messagecenter.mvp.contract

import com.example.mvp.view.IView

interface UserCenter {
    interface UserCenterView : IView{
        fun success() {

        }
        fun failed() {

        }
    }
}