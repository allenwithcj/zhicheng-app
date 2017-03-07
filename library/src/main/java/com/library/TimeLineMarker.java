package com.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Donson on 2017/1/20.
 */

public class TimeLineMarker extends View {

    private int mMarkerSize = 24;
    private int mLineSize = 9;
    private Drawable mBeginLines;
    private Drawable mEndLines;
    private Drawable mMarkerDrawable;

    public TimeLineMarker(Context context) {
        this(context,null);
    }

    public TimeLineMarker(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeLineMarker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        final TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.TimeLineMarker,0,0);
        mMarkerSize = a.getDimensionPixelSize(R.styleable.TimeLineMarker_markerSize,mMarkerSize);
        mLineSize = a.getDimensionPixelSize(R.styleable.TimeLineMarker_LineSize,mLineSize);
        mBeginLines = a.getDrawable(R.styleable.TimeLineMarker_beginLine);
        mEndLines = a.getDrawable(R.styleable.TimeLineMarker_endLine);
        mMarkerDrawable = a.getDrawable(R.styleable.TimeLineMarker_marker);
        a.recycle();
        if (mBeginLines != null){
            mBeginLines.setCallback(this);
        }
        if (mEndLines != null){
            mEndLines.setCallback(this);
        }
        if (mMarkerDrawable != null){
            mMarkerDrawable.setCallback(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBeginLines != null){
            mBeginLines.draw(canvas);
        }
        if (mEndLines != null){
            mEndLines.draw(canvas);
        }
        if (mMarkerDrawable != null){
            mMarkerDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawableSize();
    }

    private void initDrawableSize() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        int width = getWidth();
        int height = getHeight();

        int cWidth = width - pLeft - pRight;
        int cHeight = height - pTop - pBottom;

        Rect bounds;

        if (mMarkerDrawable != null) {
            // Size
            int markerSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));
            mMarkerDrawable.setBounds(pLeft, pTop, pLeft + markerSize, pTop + markerSize);
            bounds = mMarkerDrawable.getBounds();
        } else {
            bounds = new Rect(pLeft, pTop, pLeft + cWidth, pTop + cHeight);
        }

        int halfLineSize = mLineSize >> 1;
        int lineLeft = bounds.centerX() - halfLineSize;

        if (mBeginLines != null) {
            mBeginLines.setBounds(lineLeft, 0, lineLeft + mLineSize, bounds.top);
        }

        if (mEndLines != null) {
            mEndLines.setBounds(lineLeft, bounds.bottom, lineLeft + mLineSize, height);
        }
    }

    public void setLineSize(int lineSize) {
        if (mLineSize != lineSize) {
            this.mLineSize = lineSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            initDrawableSize();
            invalidate();
        }
    }

    public void setBeginLine(Drawable beginLine) {
        if (this.mBeginLines != beginLine) {
            this.mBeginLines = beginLine;
            if (mBeginLines != null) {
                mBeginLines.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setEndLine(Drawable endLine) {
        if (this.mEndLines != endLine) {
            this.mEndLines = endLine;
            if (mEndLines != null) {
                mEndLines.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    public void setMarkerDrawable(Drawable markerDrawable) {
        if (this.mMarkerDrawable != markerDrawable) {
            this.mMarkerDrawable = markerDrawable;
            if (mMarkerDrawable != null) {
                mMarkerDrawable.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }
}
