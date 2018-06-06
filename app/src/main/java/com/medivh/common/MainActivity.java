package com.medivh.common;


import com.medivh.cola.core.activities.ProxyActivity;
import com.medivh.cola.core.delegates.LatteDelegate;
import com.medivh.cola.core.delegates.launcher.LauncherDelegate;


public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
