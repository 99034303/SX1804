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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BottomTabLayout :ViewGroup{
    private var mBackgroundColor=0
    private var tabs= mutableListOf<BottomTabItemView>()
    private var immediateTabs=mutableListOf<BottomTabItemView>()
    private var itemHorizontalSpac=0
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
    private var smallerAnimation:ScaleAnimation?=null
    private var largerAnimation:ScaleAnimation?=null
    private var clickIndex=0

    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(attrs)
    }

    //初始化
    private fun init(attrs: AttributeSet?=null){
        setPadding(40,0,40,0)
        //获取自定义属性
        if(attrs!=null){
            var t=context.obtainStyledAttributes(attrs,R.styleable.BottomTabLayout)
            mBackgroundColor=t.getColor(R.styleable.BottomTabLayout_bottomTabLayoutColor, Color.parseColor("#FAFAFA"))
            t.recycle()
        }
    }

    //外部点击事件
    fun setOnItemClickListener(onItemClickListener:(Int)->Unit){
        this.onItemClickListener=onItemClickListener
    }

    //添加标题方法
    fun addTab(id:Int,resourcesId:Int,unReadMsgCount:Int=0){
        //实例化子view
        var item=BottomTabItemView(context)
        item.setImageResource(resourcesId)
        item.setBackgroundColor(mBackgroundColor)
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
    private fun getResetViewCount():Int{
        return if(clickIndex<selectIndex) (selectIndex-clickIndex) else (clickIndex-selectIndex)
    }

    //克隆子控件
    private fun cloneChild(view: BottomTabItemView):BottomTabItemView{
        var cloneView=BottomTabItemView(context)
        cloneView.setBackgroundColor(mBackgroundColor)
        cloneView.setImageDrawable(view.drawable)
        cloneView.setMId(view.getMId())
        cloneView.setMsgCount(view.getMsgCount())
        return cloneView
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
            setMeasuredDimension(LayoutParams.MATCH_PARENT,250)
        }else if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,250)
        }else if(widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(LayoutParams.MATCH_PARENT,heightSize)
        }else{
            setMeasuredDimension(widthSize,heightSize)
        }
        //如果有标题则计算相关位置
        if(isNeedCount()){
            itemHorizontalSpac=measuredWidth/tabs.size/2
            //计算选中item的宽高
            selectedItemWidth=(measuredWidth-paddingLeft-paddingRight)/tabs.size
            selectedItemWidth+=selectedItemWidth/2-itemHorizontalSpac
            selectedItemheight=measuredHeight
            //计算每个子view的宽度/高度
            itemWidth=selectedItemWidth/2
            itemHeight=selectedItemheight/2
            if(!isClick){
                //计算选中view的左上位置
                selectedVLeft=(selectIndex*itemWidth)+(itemHorizontalSpac*selectIndex)+paddingLeft
                selectedVRight=selectedVLeft+selectedItemWidth
            }else{
                isClick=false
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if(isNeedCount()){
            //view的左上右下
            var vLeft=paddingLeft
            var vRight=0
            vTop=(measuredHeight-itemHeight)/2
            vBottom=vTop+itemHeight
            //选中item后的view的计数
            var afterSelectedCount=0
            for(i in 0 until tabs.size){
                //获取子view
                var view=tabs.get(i)
                //判断是否统一计算vLeft(因为选中view后的view计算vLeft特殊)
                if(i>0&&i<selectIndex){
                    vLeft=vRight+itemHorizontalSpac
                }
                //判断是否是选中的item
                if(i==selectIndex){
                    view?.layout(selectedVLeft,0,selectedVRight,selectedItemheight)
                    largerAnimation?.cancel()
                }else if(i<selectIndex){//选中view前的view位置计算
                    vRight=vLeft+itemWidth
                    view?.layout(vLeft,vTop,vRight,vBottom)
                }else{//选中view后的view的布局计算
                    if(afterSelectedCount==0){
                        vLeft=selectedVRight+itemHorizontalSpac
                    }else{
                        vLeft=vRight+itemHorizontalSpac
                    }
                    vRight=vLeft+itemWidth
                    view?.layout(vLeft,vTop,vRight,vBottom)
                    afterSelectedCount++
                }
                if(i==clickIndex){
                    smallerAnimation?.cancel()
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

    //检查是否选中有效范围
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
                //判断是否需要执行动画等后续事件
                if(i!==selectIndex){
                    clickIndex=i
                    //判断左移还是右移执行后续事件(动画),如果选中的是已经被选中的则不做任何事情
                    if(i<selectIndex){//右移
                        isAnimationStarted=true
                        needMovePX=(selectedVRight-(selectedItemWidth/2))-(view.right-(itemWidth/2))
                        pxBymm=-(needMovePX/50)
                    }else if(i>selectIndex){
                        isAnimationStarted=true
                        needMovePX=(view.right-(itemWidth/2))-(selectedVRight-(selectedItemWidth/2))
                        pxBymm=needMovePX/50
                    }
                    executeAnimationAndReLayout(pxBymm)
                }else{
                    isClick=false
                }
                break
            }
        }
    }

    //执行动画
    private fun executeAnimationAndReLayout(pxBymm:Int){
        //衔接准备
        var count=getResetViewCount()-1
        var vLeft=0
        var vRight=0
        if(pxBymm>0){//左移
            var lastVRight=tabs.get(tabs.size-1).right
            //循环添加到临时集合
            for(i in 0..count){
                var cloneChild = cloneChild(tabs.get(i))
                //布局
                if(i==0){
                    vLeft=lastVRight+itemHorizontalSpac
                }else{
                    vLeft=vRight+itemHorizontalSpac
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
                    vRight=tabs.get(0).left-itemHorizontalSpac
                }else{
                    vRight=vLeft-itemHorizontalSpac
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
                        executelargerAnimation()
                    }
                    scrollBy(pxBymm,0)
                }
                Thread.sleep(10)
            }
            //更新ui
            uiThread {
                reSort()
                requestLayout()
                scrollX=0
                clearImmediateView()
                isAnimationStarted=false
            }
        }
    }

    //缩小动画
    private fun executeSmallerAnimator(){
        smallerAnimation=ScaleAnimation(1f,0.5f,1f,0.5f, (selectedItemWidth/2).toFloat(), (selectedItemheight/2).toFloat())
        smallerAnimation?.duration=600
        tabs.get(selectIndex).startAnimation(smallerAnimation)
    }

    //放大动画
    private fun executelargerAnimation(){
        largerAnimation=ScaleAnimation(1f,1.9f,1f,1.9f, (itemWidth/2).toFloat(), (itemHeight/2).toFloat())
        largerAnimation?.duration=600
        tabs.get(clickIndex).startAnimation(largerAnimation)
    }

    //选中后重新排序
    private fun reSort(){
        var reSortList= mutableListOf<BottomTabItemView>()
        var tabSize=tabs.size-1
        //需要重置位置的数量,其他向后直接添加
        var count=0
        //根据选中位置对集合重新排序
        if(clickIndex<selectIndex){
            count=getResetViewCount()-1
            for(i in tabSize-count..tabSize ){
                reSortList.add(tabs.get(i))
            }
            for(i in 0 until tabSize-count){
                reSortList.add(tabs.get(i))
            }
            tabs.clear()
            tabs.addAll(reSortList)
        }else if(clickIndex>selectIndex){
            count=getResetViewCount()-1
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