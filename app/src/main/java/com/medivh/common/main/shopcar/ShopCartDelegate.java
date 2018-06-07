package com.medivh.common.main.shopcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.medivh.cola.core.delegates.bottom.BottomItemDelegate;
import com.medivh.common.R;
import com.medivh.common.databinding.DelegateShopCarBinding;


public class ShopCartDelegate extends BottomItemDelegate<DelegateShopCarBinding> {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.delegate_shop_car;
    }
}
