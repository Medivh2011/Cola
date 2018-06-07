package com.medivh.cola.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;


import com.medivh.cola.core.R;
import com.medivh.cola.core.delegates.BaseDelegate;


import me.yokeyword.fragmentation.SupportActivity;

public  abstract class ProxyActivity extends SupportActivity {

    public abstract BaseDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState)
    {
        @SuppressLint("RestrictedApi")
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if ( null == savedInstanceState)
        {
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
