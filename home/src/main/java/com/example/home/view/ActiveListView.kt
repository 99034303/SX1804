package com.example.home.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.home.R

class ActiveListView:LinearLayout {
    private lateinit var view:View
    private lateinit var listHomeMainActiveList:RecyclerView
    private lateinit var imgHiddenOrShow:ImageView
    private var isShow=true
    private var isClicked=false

    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init() {
        view=LayoutInflater.from(context).inflate(R.layout.layout_home_main_activity_list,null)
        imgHiddenOrShow=view.findViewById(R.id.img_hiddenOrShow)
        //显示隐藏点击事件
        imgHiddenOrShow.setOnClickListener {
            if(!isClicked){
                isClicked=true
                if(isShow){
                    imgHiddenOrShow.setImageResource(R.mipmap.next)
                    hidden()
                }else{
                    imgHiddenOrShow.setImageResource(R.mipmap.last)
                    show()
                }
            }
        }
        listHomeMainActiveList=view.findViewById(R.id.list_home_main_activeList)
        listHomeMainActiveList.layoutManager=LinearLayoutManager(context)
        addView(view)
    }

    //设置适配器
    fun setAdapter(adapter: BaseQuickAdapter<*,BaseViewHolder>){
        this.listHomeMainActiveList.adapter=adapter
    }

    //隐藏
    private fun hidden(){
        listHomeMainActiveList.visibility=View.GONE
        var hiddenAnimator=ObjectAnimator.ofFloat(this,"translationX",-550f)
        hiddenAnimator.duration=500
        hiddenAnimator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                isClicked=false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        hiddenAnimator.start()
        isShow=false
    }

    //展示
    private fun show(){
        listHomeMainActiveList.visibility= View.VISIBLE
        var showAnimator=ObjectAnimator.ofFloat(this,"translationX",0f)
        showAnimator.duration=500
        showAnimator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                isClicked=false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
        showAnimator.start()
        isShow=true
    }
}