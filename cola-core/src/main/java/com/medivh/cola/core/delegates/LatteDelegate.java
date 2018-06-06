package com.medivh.cola.core.delegates;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public  abstract class LatteDelegate< T extends ViewDataBinding > extends PermissionCheckerDelegate {

    protected T mBinding;

    protected Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        mHandler = new Handler(Looper.getMainLooper());
        onBindView(savedInstanceState,mBinding.getRoot());
        return mBinding.getRoot();
    }

    public abstract void onBindView( @Nullable Bundle savedInstanceState,View rootView);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
    }
    
   

    public T getBinding() {

        return mBinding;
    }


    public Activity getProxyActivity() {
        return this.getActivity();
    }

    public void startCameraWithCheck() {
    }
}
