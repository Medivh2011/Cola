package com.medivh.latte.core.delegates.bottom;

import android.databinding.ViewDataBinding;
import android.widget.Toast;


import com.medivh.latte.core.delegates.LatteDelegate;

public abstract class BottomItemDelegate<T extends ViewDataBinding> extends LatteDelegate<T>  {

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击返回键退出应用", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
