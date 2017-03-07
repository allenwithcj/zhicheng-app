package com.library;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;

/**
 * Created by Donson on 2017/1/18.
 */

public class NullAutoCompleteTextView extends AutoCompleteTextView {

    public NullAutoCompleteTextView(Context context) {
        super(context);
    }

    public NullAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NullAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        performFiltering(getText(), KeyEvent.KEYCODE_UNKNOWN);
    }
}
