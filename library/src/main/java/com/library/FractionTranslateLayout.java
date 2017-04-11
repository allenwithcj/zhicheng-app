package com.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Donson on 2017/1/18.
 */

public class FractionTranslateLayout extends RelativeLayout {

    private int screenWidth;
    private float fractionX;
    private OnLayoutTranslateListener onLayoutTranslateListener;

    public FractionTranslateLayout(Context context) {
        super(context);
    }

    public FractionTranslateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FractionTranslateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        screenWidth = w;

        super.onSizeChanged(w, h, oldw, oldh);
    }

    public float getFractionX() {
        return fractionX;
    }

    public void setFractionX(float xFraction) {
        this.fractionX = xFraction;
        float xFra;

        // When we modify the xFraction, we want to adjust the x translation
        // accordingly.  Here, the scale is that if xFraction is -1, then
        // the layout is off screen to the left, if xFraction is 0, then the
        // layout is exactly on the screen, and if xFraction is 1, then the
        // layout is completely offscreen to the right.
        if (screenWidth > 0) {
            xFra = xFraction * screenWidth;
        } else {
            xFra = 0;
        }
        setX(xFra);

        if (xFraction == 1 || xFraction == -1) {
            setAlpha(0);
        } else if (xFraction < 1 /* enter */ || xFraction > -1 /* exit */) {
            if (getAlpha() != 1) {
                setAlpha(1);
            }
        }

        if (onLayoutTranslateListener != null) {
            onLayoutTranslateListener.onLayoutTranslate(this, xFraction);
        }
    }

    public void setOnLayoutTranslateListener(OnLayoutTranslateListener onLayoutTranslateListener) {
        this.onLayoutTranslateListener = onLayoutTranslateListener;
    }

    public static interface OnLayoutTranslateListener {
        void onLayoutTranslate(FractionTranslateLayout view, float xFraction);
    }
}
