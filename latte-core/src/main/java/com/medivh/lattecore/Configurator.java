package com.medivh.lattecore;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;


public class Configurator {

    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator (){

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }


    public final HashMap<String,Object> getLatteConfigs()
    {
        return LATTE_CONFIGS;
    }


    private static class Holder{

        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure()
    {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private final void initIcons()
    {
        if ( ICONS.size() > 0)
        {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for ( int i = 1 ; i < ICONS.size(); i++)
            {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor)
    {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withApiHost(String apiHost)
    {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),apiHost);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady)
        {
            throw new RuntimeException("Configurations is not ready,please call configure");
        }

    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key)
    {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }


}
