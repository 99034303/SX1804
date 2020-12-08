package com.example.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.marginRight

class BottomTabLayout :LinearLayout{
    private var tabs= mutableListOf<BottomTabItemView>()
    private var itemHorizontalSpac=20
    private var itemWidth=0
    private var itemHeight=0;
    private var selectedItemWidth=0;
    private var selectedItemheight=0

    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //初始化
    private fun init(){
        setWillNotDraw(false)
        //横向排序
        orientation= HORIZONTAL
    }

    fun addTab(resourcesId:Int,unReadMsgCount:Int=0){
        //实例化子view
        var item=BottomTabItemView(context)
        item.setImageResource(resourcesId)
        if(unReadMsgCount!=0){
            item.setMsgCount(unReadMsgCount)
        }
        //添加到集合
        tabs.add(item)
        //添加到视图
        addView(item)
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //计算每个子view的宽度/高度
        itemWidth=measuredWidth/tabs.size-itemHorizontalSpac
        itemHeight=measuredHeight-(measuredHeight/3)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        //获取普通view的top
        var vTop=t+((measuredHeight-itemHeight)/2)
        for(i in 0 until childCount){
            //获取子view
            var view=getChildAt(i)
            //获取普通view的left
            var vLeft=itemWidth*(i)+(itemHorizontalSpac*(i))+paddingLeft
            view.layout(vLeft,vTop,vLeft+itemWidth-paddingRight,vTop+itemHeight)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}