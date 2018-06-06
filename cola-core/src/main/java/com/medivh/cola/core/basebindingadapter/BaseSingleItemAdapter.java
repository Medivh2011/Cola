package com.medivh.cola.core.basebindingadapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

/**
 *
 * @author Medivh
 * @date 2017/12/28
 */

public abstract  class BaseSingleItemAdapter<T> extends BaseBindingAdapter<T> {

    private int layoutRes;

    public BaseSingleItemAdapter(Context context , @LayoutRes int layoutRes) {
        super(context);
        this.layoutRes = layoutRes;
    }
    public void setLayoutRes(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
    }
    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, layoutRes, parent, false));
    }

}
