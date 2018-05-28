package com.medivh.common;




import com.medivh.core.activities.ProxyActivity;
import com.medivh.core.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new SimpleDelegate();
    }

}
