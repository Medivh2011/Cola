package com.medivh.latte.core.delegates.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;

import com.medivh.latte.core.R;
import com.medivh.latte.core.databinding.DelegateScrollLauncherBinding;
import com.medivh.latte.core.delegates.LatteDelegate;
import com.medivh.latte.core.ui.launcher.LauncherHolderCreator;
import com.medivh.latte.core.ui.launcher.ScrollLauncherTag;
import com.medivh.latte.core.utils.LattePreference;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LatteDelegate<DelegateScrollLauncherBinding> {

    private final ArrayList<Integer> mIntegers = new ArrayList<>();

    private void initBanner(){
        mIntegers.add(R.mipmap.launcher_01);
        mIntegers.add(R.mipmap.launcher_02);
        mIntegers.add(R.mipmap.launcher_03);
        mIntegers.add(R.mipmap.launcher_04);
        mIntegers.add(R.mipmap.launcher_05);
        mBinding.banner.setPages(new LauncherHolderCreator(),mIntegers)
                        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setOnItemClickListener((i)-> {
                                Log.e("TAG","click_pagespostion: "+ i);
                                if (i == (mIntegers.size() - 1) )
                                {
                                    LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);


                                } })
                        .setCanLoop(false);


    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initBanner();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_scroll_launcher;
    }
}
