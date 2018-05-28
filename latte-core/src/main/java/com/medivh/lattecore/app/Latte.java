package com.medivh.lattecore.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        if (Configurator.getInstance().getLatteConfigs().get(ConfigKeys.HANDLER) == null)
        {
            Configurator.getInstance().getLatteConfigs().put(ConfigKeys.HANDLER,new Handler(Looper.getMainLooper()));
        }
        return getConfiguration(ConfigKeys.HANDLER);
    }
}