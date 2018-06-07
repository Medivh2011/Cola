package com.medivh.common;

import com.medivh.cola.core.delegates.launcher.ColaLauncherDelegate;
import com.medivh.cola.core.delegates.launcher.ColaLauncherScrollDelegate;
import com.medivh.common.databinding.DelegateLauncherBinding;

public class LauncherDelegate extends ColaLauncherDelegate<DelegateLauncherBinding> {

    @Override
    protected void startLauncherScroll() {

        start(new ColaLauncherScrollDelegate(),SINGLETASK);

    }
    @Override
    protected void startMainDelegate() {
        start(new EcBottomDelegate(),SINGLETASK);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBinding.setImgUrl("http://img02.tooopen.com/products/20150509/tooopen_36559872.jpg");
    }
}
