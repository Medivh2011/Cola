package com.medivh.cola.core.basebindingadapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 *
 * @author Medivh
 * @date 2017/12/26
 */

public class BindingViewHolder <T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding ;
    }
    public T getBinding() {
        return mBinding;
    }
}
