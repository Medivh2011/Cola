package com.medivh.common;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;


import com.medivh.cola.core.delegates.bottom.BottomDelegate;
import com.medivh.cola.core.delegates.bottom.BottomItemDelegate;
import com.medivh.cola.core.delegates.bottom.BottomTabBean;
import com.medivh.cola.core.delegates.bottom.ItemBuilder;
import com.medivh.common.databinding.DelegateEcbottomBinding;

import java.util.LinkedHashMap;


@SuppressWarnings("unused")
public class EcBottomDelegate extends BottomDelegate<DelegateEcbottomBinding> {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }
    @Override
    public int setIndexDelegate() {
        return 0;
    }
    @Override
    public int setClickedColor() {

        return Color.parseColor("#ffff8800");
    }
    @NonNull
    @Override
    public LinearLayoutCompat getBottomItemView() {
        return mBinding.bottomBar;
    }
    @Override
    public int getContainerId() {
        return R.id.bottom_bar_delegate_container;
    }
    @Override
    protected int getLayoutId()
    {
        return R.layout.delegate_ecbottom;
    }
}
