package com.medivh.common.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.medivh.cola.core.delegates.bottom.BottomItemDelegate;
import com.medivh.common.R;
import com.medivh.common.databinding.DelegateDiscoverBinding;


public  class DiscoverDelegate extends BottomItemDelegate<DelegateDiscoverBinding> {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_discover;
    }
}
