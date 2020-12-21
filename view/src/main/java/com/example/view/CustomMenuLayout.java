package com.example.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;


public class CustomMenuLayout extends CoordinatorLayout {
    private int width = 500;
    private int height = 500;
    private Context context;
    private int hideDirection = 2;
    private CheckBox checkBox;
    private float start = 0;
    private float end;

    public CustomMenuLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CustomMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //自定义属性
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomMenuLayout);
        hideDirection= attributes.getInteger(R.styleable.CustomMenuLayout_hideDirection,2);
        init(context);
    }


    public CustomMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);//重点，不加这句子控件无法获取宽高
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int _width = MeasureSpec.getSize(widthMeasureSpec);
        int _height = MeasureSpec.getSize(heightMeasureSpec);
        if (widthModel == MeasureSpec.AT_MOST && heightModel == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height);
        } else if (widthModel == MeasureSpec.AT_MOST) {
            height = _height;
            setMeasuredDimension(width, height);
        } else if (heightModel == MeasureSpec.AT_MOST) {
            width = _width;
            setMeasuredDimension(width, height);
        } else {
            width = _width;
            height = _height;
            setMeasuredDimension(width, height);
        }

        end = width - checkBox.getMeasuredWidth();
    }

    private void init(Context context) {
        this.context = context;
        checkBox = new CheckBox(context);

        checkBox.setButtonDrawable(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkBox.setElevation(10f);
        }
        LayoutParams params = new LayoutParams(50, 50);
        switch (hideDirection) {
            case 0://left
                params.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
                checkBox.setBackgroundResource(R.drawable.check_box);
                checkBox.setChecked(true);
                this.setPadding(10,0,0,0);
                break;
            case 2://right
                params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
                checkBox.setBackgroundResource(R.drawable.check_box_right);
                break;
        }
        checkBox.setLayoutParams(params);
        checkBox.setOnCheckedChangeListener((c, f) -> {
            if (f) {
                show();
            } else {
                hide();
            }
        });
        addView(checkBox);
    }

    private void hide() {
        getObjectAnimator(-end, -start);
    }

    private void show() {
        getObjectAnimator(start, end);
    }

    private ValueAnimator getAnimator(float start, float end) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            switch (hideDirection) {
                case 0://left
                    this.setX(value);
                    break;
                case 2://right
                    this.setX(-value);
                    break;
            }
        });


        return valueAnimator;
    }

    private void getObjectAnimator(float start, float end) {
        ObjectAnimator translationX;
        if (hideDirection == 0) {

            translationX = ObjectAnimator.ofFloat(this, "translationX", end, -start);
        } else {
            translationX = ObjectAnimator.ofFloat(this, "translationX", start, -end);

        }
        translationX.setDuration(500)
                .start();
    }

    public int getHideDirection() {
        return hideDirection;
    }

    public void setHideDirection(int hideDirection) {
        this.hideDirection = hideDirection;
    }
    public static int LEFT = 0;
    public static int RIGHT = 0;
}
