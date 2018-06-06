package com.medivh.latte.core.delegates.launcher;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.medivh.latte.core.R;
import com.medivh.latte.core.databinding.DelegateLauncherBinding;
import com.medivh.latte.core.delegates.LatteDelegate;
import com.medivh.latte.core.ui.launcher.ScrollLauncherTag;
import com.medivh.latte.core.utils.LattePreference;
import com.medivh.latte.core.utils.timer.BaseTimerTask;

import java.text.MessageFormat;
import java.util.Timer;
public class LauncherDelegate extends LatteDelegate<DelegateLauncherBinding> {


    private Timer mTimer;

    private int mCount = 5;

    public void jump()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScrollDelegate();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_launcher;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState,View rootView) {
        initTimer();
        mBinding.tvLauncherTimer.setOnClickListener(v->jump());
    }

    //判断是否需要显示滑动启动页
    private void checkIsShowScrollDelegate()
    {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()))
        {
            start(new LauncherScrollDelegate(),SINGLETASK);
        }

    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(()-> mHandler.post(()->{
        mBinding.tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
        mCount--;
        if (mCount < 0)
        {
            if (mTimer != null)
            {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScrollDelegate();
            }
        }
        }));
        mTimer.schedule(timerTask,0,1000);
    }
}
