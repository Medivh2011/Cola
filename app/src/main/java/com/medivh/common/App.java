package com.medivh.common;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.medivh.cola.core.app.Cola;
import com.medivh.cola.core.font.FontEcModule;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Cola.init(this)
                .withLoaderDelayed(1000)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
