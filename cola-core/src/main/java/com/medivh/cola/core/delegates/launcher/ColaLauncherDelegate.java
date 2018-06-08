package com.medivh.cola.core.delegates.launcher;



import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medivh.cola.core.R;
import com.medivh.cola.core.delegates.PermissionCheckerDelegate;
import com.medivh.cola.core.ui.launcher.ScrollLauncherTag;
import com.medivh.cola.core.utils.ColaPreference;
import com.medivh.cola.core.utils.PixelUtils;
import com.medivh.cola.core.utils.timer.BaseTimerTask;

import java.text.MessageFormat;
import java.util.Timer;


public  abstract class ColaLauncherDelegate<T extends ViewDataBinding> extends PermissionCheckerDelegate {

    private Timer mTimer;

    private int mWaittingTime = 5;

    protected T mBinding;

    protected Handler mHandler;

    private TextView mLauncherTimer;

    public void jump()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScrollDelegate();
        }
    }
    //判断是否需要显示滑动启动页
    private void checkIsShowScrollDelegate()
    {
        if (!ColaPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()))
        {
            startLauncherScroll();
        }else {
            startMainDelegate();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        mHandler = new Handler(Looper.getMainLooper());
        initTimer();
        return mBinding.getRoot();
    }
    protected abstract void startLauncherScroll();

    protected abstract void startMainDelegate();

    private void initTimer(){
        if ( null == mLauncherTimer)
        {
            mLauncherTimer = new TextView(_mActivity);
            mLauncherTimer.setWidth(PixelUtils.dp2px(_mActivity,60f));
            mLauncherTimer.setHeight(PixelUtils.dp2px(_mActivity,60f));
            mLauncherTimer.setGravity(Gravity.CENTER);
            mLauncherTimer.setBackgroundResource(R.drawable.border_circle_timer);
            mLauncherTimer.setOnClickListener( v -> jump());
            layoutLauncherTimer();
        }
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(()-> mHandler.post(()->{
        mLauncherTimer.setText(MessageFormat.format("跳过\n{0}s",mWaittingTime));
         mWaittingTime --;
        if (mWaittingTime < 0)
        {
            if (mTimer != null)
            {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScrollDelegate();
            }
        } }));
        mTimer.schedule(timerTask,0,1000);
    }
    private void layoutLauncherTimer() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                ,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.setMargins(0,PixelUtils.dp2px(_mActivity,15f),PixelUtils.dp2px(_mActivity,15f),0);
        mLauncherTimer.setLayoutParams(layoutParams);
        if (mBinding.getRoot() instanceof RelativeLayout)
        {
            ((ViewGroup)mBinding.getRoot()).addView(mLauncherTimer);
        }else {
            throw new RuntimeException(" LauncherDelegate rootView must be RelativeLayout");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if ( null != mHandler)
        {
            mHandler = null ;
            mBinding.unbind();
        }

    }
}
