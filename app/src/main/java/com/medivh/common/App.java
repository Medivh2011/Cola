package com.medivh.common;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.medivh.latte.ec.font.FontEcModule;
import com.medivh.lattecore.app.Latte;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withLoaderDelayed(1000)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
