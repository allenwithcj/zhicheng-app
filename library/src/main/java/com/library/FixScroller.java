package com.library;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Donson on 2017/1/16.
 */

public class FixScroller extends Scroller {

    private int mDuration = 1500;

    public void setDuration(int du){
        this.mDuration = du;
    }

    public FixScroller(Context context) {
        super(context);
    }

    public FixScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy,mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
