package com.medivh.common.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.medivh.cola.core.delegates.bottom.BottomItemDelegate;
import com.medivh.common.R;
import com.medivh.common.databinding.DelegateSortBinding;


public class SortDelegate extends BottomItemDelegate<DelegateSortBinding> {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_sort;
    }
}
