package com.medivh.common.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.medivh.cola.core.delegates.bottom.BottomItemDelegate;
import com.medivh.common.R;
import com.medivh.common.databinding.DelegatePersonalBinding;


public class PersonalDelegate extends BottomItemDelegate<DelegatePersonalBinding> {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_personal;
    }
}
