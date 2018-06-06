package com.medivh.common;


import com.medivh.latte.core.activities.ProxyActivity;
import com.medivh.latte.core.delegates.LatteDelegate;
import com.medivh.latte.core.delegates.launcher.LauncherDelegate;


public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
