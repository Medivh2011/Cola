package com.medivh.cola.core.delegates.launcher;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.medivh.cola.core.R;
import com.medivh.cola.core.databinding.DelegateLauncherBinding;
import com.medivh.cola.core.delegates.LatteDelegate;
import com.medivh.cola.core.ui.launcher.ScrollLauncherTag;
import com.medivh.cola.core.utils.LattePreference;
import com.medivh.cola.core.utils.timer.BaseTimerTask;

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
        mBinding.setImgUrl("http://img02.tooopen.com/products/20150509/tooopen_36559872.jpg");
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
