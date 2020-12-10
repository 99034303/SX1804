package com.example.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.marginRight
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.math.log

class BottomTabLayout :ViewGroup{
    private var tabs= mutableListOf<BottomTabItemView>()
    private var immediateTabs=mutableListOf<BottomTabItemView>()
    private var itemHorizontalSpac=20
    private var itemWidth=0
    private var itemHeight=0;
    private var selectedItemWidth=0;
    private var selectedItemheight=0
    private var selectIndex=0
    private var selectedVLeft=0
    private var selectedVRight=0
    //如果是点击则不重新计算选中view的相关位置
    private var isClick=false
    private var onItemClickListener:((Int)->Unit)?=null
    private var vTop=0
    private var vBottom=0
    //避免重复点击
    private var isAnimationStarted=false

    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //初始化
    private fun init(){

    }

    fun setOnItemClickListener(onItemClickListener:(Int)->Unit){
        this.onItemClickListener=onItemClickListener
    }

    //添加标题方法
    fun addTab(id:Int,resourcesId:Int,unReadMsgCount:Int=0){
        //实例化子view
        var item=BottomTabItemView(context)
        item.setImageResource(resourcesId)
        if(unReadMsgCount!=0){
            item.setMsgCount(unReadMsgCount)
        }
        item.setMId(id)
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

    //检查是否需要布局计算
    private fun isNeedCount():Boolean{
        if(tabs.size>0){
            return true
        }
        return false
    }

    //获取需要重新设置位置view的数量(非下标)
    private fun getResetViewCount(clickIndex: Int):Int{
        return if(clickIndex<selectIndex) (selectIndex-clickIndex) else (clickIndex-selectIndex)
    }

    //克隆子控件
    private fun cloneChild(view: BottomTabItemView):BottomTabItemView{
        var clonView=BottomTabItemView(context)
        clonView.setImageDrawable(view.drawable)
        clonView.setMId(view.getMId())
        clonView.setMsgCount(view.getMsgCount())
        return clonView
    }

    //从容器中删除临时view
    private fun clearImmediateView(){
        for(i in immediateTabs){
            removeView(i)
        }
        immediateTabs.clear()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMode=MeasureSpec.getMode(heightMeasureSpec)
        var widthMode=MeasureSpec.getMode(widthMeasureSpec)
        var heightSize=MeasureSpec.getSize(heightMeasureSpec)
        var widthSize=MeasureSpec.getSize(widthMeasureSpec)
        if(heightMode==MeasureSpec.AT_MOST&&widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(LayoutParams.MATCH_PARENT,300)
        }else if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,300)
        }else if(widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(LayoutParams.MATCH_PARENT,heightSize)
        }else{
            setMeasuredDimension(widthSize,heightSize)
        }
        //如果有标题则计算相关位置
        if(isNeedCount()){
            //计算选中item的宽高
            selectedItemWidth=measuredWidth/tabs.size-itemHorizontalSpac
            selectedItemWidth+=selectedItemheight/2
            selectedItemheight=measuredHeight
            //计算每个子view的宽度/高度
            itemWidth=(measuredWidth-selectedItemWidth)/tabs.size-itemHorizontalSpac
            itemHeight=measuredHeight-(measuredHeight/3)
            if(!isClick){
                //计算选中view的左上位置
                selectedVLeft=(selectIndex*itemWidth)+(itemHorizontalSpac*selectIndex)+(paddingLeft*(selectIndex+1))
                selectedVRight=selectedVLeft+selectedItemWidth
            }else{
                isClick=false
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if(isNeedCount()){
            //view的左上右下
            var vLeft=0
            var vRight=0
            vTop=t+((measuredHeight-itemHeight)/2)
            vBottom=vTop+itemHeight
            //选中item后的view的计数
            var afterSelectedCount=0
            for(i in 0 until tabs.size){
                //获取子view
                var view=tabs.get(i)
                //判断是否统一计算vLeft(因为选中view后的view计算vLeft特殊)
                if(i<selectIndex){
                    vLeft+=vRight+paddingLeft
                }
                //判断是否是选中的item
                if(i==selectIndex){
                    view?.layout(selectedVLeft,0,selectedVRight,selectedItemheight)
                }else if(i<selectIndex){//选中view前的view位置计算
                    vRight=vLeft+itemWidth
                    view?.layout(vLeft,vTop,vRight,vBottom)
                }else{//选中view后的view的布局计算
                    if(afterSelectedCount==0){
                        vLeft=selectedVRight+((afterSelectedCount+1)*itemHorizontalSpac)+(afterSelectedCount*itemWidth)
                    }else{
                        vLeft=vRight+itemHorizontalSpac+paddingLeft
                    }
                    vRight=vLeft+itemWidth
                    view?.layout(vLeft,vTop,vRight,vBottom)
                    afterSelectedCount++
                }
            }
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(!isAnimationStarted){
            if(ev?.action==MotionEvent.ACTION_DOWN){
                checkedIsSelect(ev)
                return true
            }
        }
        return false
    }

    private fun checkedIsSelect(ev: MotionEvent?){
        var x=ev?.x!!
        var y=ev?.y!!
        var needMovePX=0
        var pxBymm=0
        for(i in 0 until tabs.size){
            var view=tabs.get(i)!!
            //判断是否选中有效范围
            if(x>=view.left&&x<=view.right&&y>=view.top&&y<=view.bottom){
                isClick=true
                //执行外部重写的点击事件
                if(onItemClickListener!=null){
                    onItemClickListener!!(view.getMId())
                }
                //判断左移还是右移执行后续事件(动画),如果选中的是已经被选中的则不做任何事情
                if(i<selectIndex){//右移
                    isAnimationStarted=true
                    needMovePX=selectedVLeft-view.left
                    pxBymm=-(needMovePX/50)
                    executeAnimationAndReLayout(i,pxBymm)
                }else if(i>selectIndex){
                    isAnimationStarted=true
                    needMovePX=view.right-selectedVRight+paddingLeft
                    pxBymm=needMovePX/50
                    executeAnimationAndReLayout(i,pxBymm)
                }else{
                    isClick=false
                }
                break
            }
        }
    }

    //执行动画
    private fun executeAnimationAndReLayout(clickIndex: Int,pxBymm:Int){
        //衔接准备
        var count=getResetViewCount(clickIndex)-1
        var vLeft=0
        var vRight=0
        if(pxBymm>0){//左移
            var lastVRight=tabs.get(tabs.size-1).right
            //循环添加到临时集合
            for(i in 0..count){
                var cloneChild = cloneChild(tabs.get(i))
                //布局
                if(i==0){
                    vLeft=lastVRight+paddingLeft+itemHorizontalSpac
                }else{
                    vLeft=vRight+paddingLeft+itemHorizontalSpac
                }
                vRight=vLeft+itemWidth
                cloneChild.layout(vLeft,vTop,vLeft+itemWidth,vTop+itemHeight)
                //添加到集合
                immediateTabs.add(cloneChild)
                //添加到容器
                addView(cloneChild)
            }
        }else{
            var lastViewIndex=tabs.size-1
            for(i in lastViewIndex downTo lastViewIndex-count){
                var cloneChild = cloneChild(tabs.get(i))
                if(i==lastViewIndex){
                    vRight=tabs.get(0).left-itemHorizontalSpac-paddingLeft
                }else{
                    vRight=vLeft-itemHorizontalSpac-paddingLeft
                }
                vLeft=vRight-itemWidth
                cloneChild.layout(vLeft,vTop,vLeft+itemWidth,vTop+itemHeight)
                //添加到集合
                immediateTabs.add(cloneChild)
                //添加到容器
                addView(cloneChild)
            }
        }
        //执行动画
        doAsync {
            for(i in 0..50){
                uiThread {
                    if(i==0){
                        executeSmallerAnimator()
                        executelargerAnimation(clickIndex)
                    }
                    scrollBy(pxBymm,0)
                }
                Thread.sleep(10)
            }
            //更新ui
            uiThread {
                reSort(clickIndex)
                requestLayout()
                scrollX=0
                clearImmediateView()
                isAnimationStarted=false
            }
        }
    }

    //缩小动画
    private fun executeSmallerAnimator(){
        var scaleAnimation=ScaleAnimation(1f,0.5f,1f,0.5f,100f,100f)
        scaleAnimation.duration=500
        tabs.get(selectIndex).startAnimation(scaleAnimation)
    }

    //放大动画
    private fun executelargerAnimation(clickIndex: Int){
        var scaleAnimation=ScaleAnimation(1f,1.5f,1f,1.5f,100f,100f)
        scaleAnimation.duration=500
        tabs.get(clickIndex).startAnimation(scaleAnimation)
    }

    //选中后重新排序
    private fun reSort(clickIndex:Int){
        var reSortList= mutableListOf<BottomTabItemView>()
        var tabSize=tabs.size-1
        //需要重置位置的数量,其他向后直接添加
        var count=0
        //根据选中位置对集合重新排序
        if(clickIndex<selectIndex){
            count=getResetViewCount(clickIndex)-1
            for(i in tabSize-count..tabSize ){
                reSortList.add(tabs.get(i))
            }
            for(i in 0 until tabSize-count){
                reSortList.add(tabs.get(i))
            }
            tabs.clear()
            tabs.addAll(reSortList)
        }else if(clickIndex>selectIndex){
            count=getResetViewCount(clickIndex)-1
            for(i in count+1..tabSize){
                reSortList.add(tabs.get(i))
            }
            for(i in 0..count){
                reSortList.add(tabs.get(i))
            }
            tabs.clear()
            tabs.addAll(reSortList)
        }
    }
}