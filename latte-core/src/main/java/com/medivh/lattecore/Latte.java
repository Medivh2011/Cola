package com.medivh.lattecore;

import android.content.Context;

import java.util.HashMap;


public final class Latte {


    public  static Configurator init(Context context)
    {
        getConfiguations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());

        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfiguations()
    {
        return Configurator.getInstance().getLatteConfigs();
    }

    public  static  Context getApplicationContext()
    {

        return (Context) getConfiguations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}
