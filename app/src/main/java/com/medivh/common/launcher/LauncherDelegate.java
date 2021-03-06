package com.medivh.common.launcher;

import com.medivh.cola.core.delegates.launcher.ColaLauncherDelegate;
import com.medivh.common.R;
import com.medivh.common.databinding.DelegateLauncherBinding;
import com.medivh.common.main.EcBottomDelegate;

public class LauncherDelegate extends ColaLauncherDelegate<DelegateLauncherBinding> {

    @Override
    protected void startLauncherScroll() {

        start(new ScrollLauncherDelegate(),SINGLETASK);

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
