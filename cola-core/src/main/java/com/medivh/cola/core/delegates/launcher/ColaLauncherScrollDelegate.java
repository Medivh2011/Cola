package com.medivh.cola.core.delegates.launcher;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;

import com.medivh.cola.core.R;
import com.medivh.cola.core.delegates.PermissionCheckerDelegate;
import com.medivh.cola.core.ui.launcher.LauncherHolderCreator;
import com.medivh.cola.core.ui.launcher.ScrollLauncherTag;
import com.medivh.cola.core.utils.ColaPreference;

import java.util.ArrayList;

public abstract class ColaLauncherScrollDelegate<T extends ViewDataBinding> extends PermissionCheckerDelegate {

    private final ArrayList<Integer> mIntegers = new ArrayList<>();
    protected T mBinding;
    private ConvenientBanner mBanner;

    private void initBanner(){
        if ( null != initBannerResource() && initBannerResource().size() > 0)
        {
            mIntegers.addAll(initBannerResource());
        }else
        {
            throw new RuntimeException("GuideDeleate Banner Resource must not be empty");
        }
        mBanner.setPages(new LauncherHolderCreator(),mIntegers)
                        .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .setOnItemClickListener((i)-> {
                                Log.e("TAG","click_pagespostion: "+ i);
                                if (i == (mIntegers.size() - 1) )
                                {
                                    ColaPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
                                   startMainDelegate();
                                } })
                        .setCanLoop(false);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        if ( getBanner() != null)
        {
            if ( null == mBanner)
            {
                mBanner = getBanner();
            }
        }else {
            throw new RuntimeException("GuideDelegate Banner must not be empty");
        }
        initBanner();
        onBindView(savedInstanceState,mBinding.getRoot());
        return mBinding.getRoot();
    }

    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    protected abstract @NonNull ConvenientBanner getBanner();

    protected abstract @NonNull ArrayList<Integer> initBannerResource();

    public abstract void startMainDelegate();



}
