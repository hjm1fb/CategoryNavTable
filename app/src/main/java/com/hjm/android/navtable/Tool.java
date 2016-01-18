package com.hjm.android.navtable;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

public class Tool {


    /**
     * @return int
     * @name dip to pixel
     * @desc dip to pixel
     */
    public static int dp2px(Resources resources, float dpValue) {
        final float scale = resources.getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
        return dp2px(context.getResources(), dpValue);
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int width;
        if (android.os.Build.VERSION.SDK_INT > 12) {
            Point point = new Point();
            display.getSize(point);
            width = point.x;
        } else {
            width = display.getWidth(); // deprecated
        }
        return width;
    }
}