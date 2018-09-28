package com.dertyp7214.stylelib.layouts;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.dertyp7214.stylelib.Utils;

public class StyleBottomLineLayout extends RelativeLayout {

    private int apiLevel = 0;
    private Context context;

    public StyleBottomLineLayout(Context context) {
        super(context);
        this.context=context;
    }

    public StyleBottomLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public StyleBottomLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void enableAtApiLevel(int apiLevel) {
        this.apiLevel=apiLevel;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    private void init() {
        Log.d("LOG", apiLevel+"");
        if (Build.VERSION.SDK_INT >= apiLevel) {
            LayoutParams layoutParams =
                    new LayoutParams(LayoutParams.MATCH_PARENT, Utils.dpToPx(1));
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.bottomMargin = 2;
            View line = new View(context);
            line.setBackgroundColor(Utils.lineColor);
            addView(line, layoutParams);
        }
    }
}
