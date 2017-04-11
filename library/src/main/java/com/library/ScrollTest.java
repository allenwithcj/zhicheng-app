package com.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Donson on 2017/1/22.
 */

public class ScrollTest extends View {

    private int lastX;
    private int lastY;
    private int offsetX;
    private int offsetY;

    public ScrollTest(Context context) {
        super(context);
    }

    public ScrollTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = x - lastX;
                offsetY = y - lastY;
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
