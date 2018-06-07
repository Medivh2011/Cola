package com.medivh.common;

import android.support.annotation.NonNull;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.medivh.cola.core.delegates.launcher.ColaLauncherScrollDelegate;
import com.medivh.common.databinding.DelegateScrollLauncherBinding;


import java.util.ArrayList;

public class ScrollLauncherDelegate extends ColaLauncherScrollDelegate<DelegateScrollLauncherBinding> {

    @NonNull
    @Override
    protected ConvenientBanner getBanner() {
        return mBinding.banner;
    }

    @NonNull
    @Override
    protected ArrayList<Integer> initBannerResource() {
        final ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.mipmap.launcher_01);
        integers.add(R.mipmap.launcher_02);
        integers.add(R.mipmap.launcher_03);
        integers.add(R.mipmap.launcher_04);
        integers.add(R.mipmap.launcher_05);
        return integers;
    }

    @Override
    public void startMainDelegate() {
        start(new EcBottomDelegate(),SINGLETASK);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.delegate_scroll_launcher;
    }
}
