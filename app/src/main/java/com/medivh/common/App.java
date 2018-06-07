package com.medivh.common;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.medivh.cola.core.app.Cola;
import com.medivh.cola.core.crash.CrashHandler;
import com.medivh.cola.core.font.FontEcModule;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        initCola(this);
    }
    private void initCola(Context context) {
        Cola.init(context.getApplicationContext())
                .withLoaderDelayed(1000)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
