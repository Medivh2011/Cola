package com.medivh.cola.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.medivh.cola.core.app.Cola;


public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Cola.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Cola.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
