package com.medivh.common;




import com.medivh.lattecore.activities.ProxyActivity;
import com.medivh.lattecore.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new SimpleDelegate();
    }

}
