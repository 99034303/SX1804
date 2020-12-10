package com.example.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class BottomTabItemView :ImageView{
     private var mID:Int=-1
    private val circlePaint=Paint()
    private val textPaint=Paint()
    private var msgCount=-1
    private lateinit var bounds:Rect
    private var values= FloatArray(10)

    fun setMId(id:Int){
        this.mID=id
    }

    fun getMId():Int{
        return mID
    }

    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //初始化
    private fun init(){
        //初始化圆角画笔
        circlePaint.color=Color.RED
        //初始化文本画笔
        textPaint.color=Color.WHITE
    }

    fun setMsgCount(msgCount:Int){
        this.msgCount=msgCount
        invalidate()
    }

    fun getMsgCount():Int{
        return this.msgCount
    }

    override fun onDraw(canvas: Canvas?) {
        //重新设置图片的范围
        bounds = drawable.bounds
        resetDrawableBounds()
        super.onDraw(canvas)
        //获取缩放比例
        imageMatrix.getValues(values)
        //判断是否需要绘制圆角
        if(msgCount!=-1){
            //获取角标的半径
            var circleRadiu=if(width<height)width/9.toFloat()else height/9.toFloat()
            //根据圆角大小设置字体大小
            textPaint.textSize=circleRadiu
            //中心点
            var circleX=(width/2)+(getDrawableWidth()/2)-(getDrawableWidth()/9)
            var circleY=0f
            circleY=(((height/2)-(getDrawableHeight()/2))).toFloat()+(getDrawableHeight()/9)
            //绘制圆角
            canvas?.drawCircle(circleX,circleY,circleRadiu,circlePaint)
            //获取文本最小矩形间距
            var textBounds=getTextBounds(msgCount.toString())
            //绘制文本
            canvas?.drawText(msgCount.toString(),circleX-(textBounds.width()/2),circleY+(textBounds.height()/2),textPaint)
        }
    }

    private fun resetDrawableBounds(){
        bounds.top=bounds.bottom/9
        bounds.right=bounds.right-(bounds.right/9)
    }

    //获取图片宽度
    private fun getDrawableWidth():Float{
        return bounds.width()*values[0]
    }

    //获取图片高度
    private fun getDrawableHeight():Float{
        return bounds.height()*values[4]
    }

    //获取字体最小矩形间距
    private fun getTextBounds(str:String):Rect{
        var textBounds=Rect()
        textPaint.getTextBounds(str,0,str.length,textBounds)
        return textBounds
    }
}