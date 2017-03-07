package com.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * Created by Donson on 2017/1/19.
 */

public class NoTouchBottomButton extends BottomNavigationBar {

    private boolean isTouch = true;

    public NoTouchBottomButton(Context context) {
        super(context);
    }

    public NoTouchBottomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchBottomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !isTouch;
    }

    public void setTouch(boolean t){
        this.isTouch = true;
    }
}
