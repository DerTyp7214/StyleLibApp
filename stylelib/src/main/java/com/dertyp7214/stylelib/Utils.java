package com.dertyp7214.stylelib;

import android.content.res.Resources;
import android.graphics.Color;

public class Utils {

    public static int lineColor = Color.parseColor("#e7e7e7");

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
