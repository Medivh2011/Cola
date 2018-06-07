package com.medivh.common;


import com.medivh.cola.core.activities.ProxyActivity;
import com.medivh.cola.core.delegates.BaseDelegate;
import com.medivh.common.launcher.LauncherDelegate;


public class MainActivity extends ProxyActivity {

    @Override
    public BaseDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
