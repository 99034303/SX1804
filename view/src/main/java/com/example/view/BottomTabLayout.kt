package com.example.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class BottomTabLayout :LinearLayout{
    private var tabs= mutableListOf<BottomTabItemView>()
    private var itemHorizontalSpac=20
    private var itemWidth=0
    private var itemHeight=0;
    private var selectedItemWidth=0;
    private var selectedItemheight=0
    private var selectIndex=0
    private var selectedVLeft=0
    private var selectedVRight=0
    private var isCleck=false
    private var onItemClickListener:((Int)->Unit)?=null

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

    //选中后重新排序
    private fun reSort(clickIndex:Int){
        var reSortList= mutableListOf<BottomTabItemView>()
        var tabSize=tabs.size-1
        //需要重置位置的数量,其他向后直接添加
        var count=0
        //根据选中位置对集合重新排序
        if(clickIndex<selectIndex){
            count=(selectIndex-clickIndex)-1
            for(i in tabSize-count..tabSize ){
                reSortList.add(tabs.get(i))
            }
            for(i in 0 until tabSize-count){
                reSortList.add(tabs.get(i))
            }
            tabs.clear()
            tabs.addAll(reSortList)
        }else if(clickIndex>selectIndex){
            count=(clickIndex-selectIndex)-1
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(isNeedCount()){
            //计算选中item的宽高
            selectedItemWidth=measuredWidth/tabs.size-itemHorizontalSpac
            selectedItemWidth+=selectedItemheight/2
            selectedItemheight=measuredHeight
            //计算每个子view的宽度/高度
            itemWidth=(measuredWidth-selectedItemWidth)/tabs.size-itemHorizontalSpac
            itemHeight=measuredHeight-(measuredHeight/3)
            if(!isCleck){
                //计算选中view的左上位置
                selectedVLeft=(selectIndex*itemWidth)+(itemHorizontalSpac*selectIndex)+(paddingLeft*(selectIndex+1))
                selectedVRight=selectedVLeft+selectedItemWidth
            }else{
                isCleck=false
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if(isNeedCount()){
            //view的左上右下
            var vLeft=0
            var vRight=0
            var vTop=t+((measuredHeight-itemHeight)/2)
            var vBottom=vTop+itemHeight
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action==MotionEvent.ACTION_DOWN){
            checkedIsSelect(ev)
            return true
        }
        return false
    }

    private fun checkedIsSelect(ev: MotionEvent?){
        var x=ev?.x!!
        var y=ev?.y!!
        for(i in 0 until tabs.size){
            var view=tabs.get(i)!!
            //判断是否选中有效范围
            if(x>=view.left&&x<=view.right&&y>=view.top&&y<=view.bottom){
                isCleck=true
                if(onItemClickListener!=null){
                    onItemClickListener!!(view.getMId())
                }
//                if(i<selectIndex){
//                    var needMovePX=selectedVLeft-view.left
//                    var pxBymm=needMovePX/50
//                    doAsync {
//                        for(i in 0..50){
//                            uiThread {
//                                scrollBy(-pxBymm,0)
//                            }
//                            Thread.sleep(10)
//                        }
////                        uiThread {
////                            reSort(i)
////                            requestLayout()
////                        }
//                    }
//                }else{
//                    var needMovePX=view.left-selectedVLeft
//                    var pxBymm=needMovePX/50
//                    doAsync {
//                        for(i in 0..50){
//                            uiThread {
//                                scrollBy(pxBymm,0)
//                            }
//                            Thread.sleep(10)
//                        }
////                        reSort(i)
////                        uiThread {
////                            reSort(i)
////                            requestLayout()
////                        }
//                    }
//                }
                reSort(i)
                requestLayout()
//                scrollBy(-100,0)
                break
            }
        }
    }
}