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
    private var selectIndex=0

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
        setDefaultSelect()
        requestLayout()
    }

    //设置默认选中项
    private fun setDefaultSelect(){
        selectIndex=if(tabs.size%2==0) (tabs.size/2)-1 else tabs.size/2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //计算选中item的宽高
        selectedItemWidth=measuredWidth/tabs.size-itemHorizontalSpac
        selectedItemWidth+=selectedItemheight/2
        selectedItemheight=measuredHeight
        //计算每个子view的宽度/高度
        itemWidth=(measuredWidth-selectedItemWidth)/tabs.size-itemHorizontalSpac
        itemHeight=measuredHeight-(measuredHeight/3)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        //选中item的左上右下
        var selectedVLeft=0
        var selectedVRight=0
        //view的左上右下
        var vLeft=0
        var vRight=0
        var vTop=t+((measuredHeight-itemHeight)/2)
        var vBottom=vTop+itemHeight
        //选中item后的view的计数
        var afterSelectedCount=0
        for(i in 0 until childCount){
            //获取子view
            var view=getChildAt(i)
            //判断是否统一计算vLeft(因为选中view后的view计算vLeft特殊)
            if(i<selectIndex){
                vLeft+=vRight+paddingLeft
            }
            //判断是否是选中的item
            if(i==selectIndex){
                selectedVLeft=vRight+itemHorizontalSpac
                selectedVRight=selectedVLeft+selectedItemWidth
                view.layout(selectedVLeft,0,selectedVRight,selectedItemheight)
            }else if(i<selectIndex){//选中view前的view位置计算
                vRight=vLeft+itemWidth
                view.layout(vLeft,vTop,vRight,vBottom)
            }else{//选中view后的view的布局计算
                if(afterSelectedCount==0){
                    vLeft=selectedVRight+((afterSelectedCount+1)*itemHorizontalSpac)+(afterSelectedCount*itemWidth)
                }else{
                    vLeft=vRight+itemHorizontalSpac+paddingLeft
                }
                vRight=vLeft+itemWidth
                view.layout(vLeft,vTop,vRight,vBottom)
                afterSelectedCount++
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}