package com.medivh.latte.core.ui;

import android.content.Context;

import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public final class LoaderCreator {

    private static  final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type,Context context)
    {
        final AVLoadingIndicatorView loadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null )
        {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }

        loadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return loadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {

        if (null == name || name.isEmpty())
        {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains("."))
        {
            String defaultPackName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        Log.e("TAG","className: " + drawableClassName.toString());
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }

}
