package com.medivh.cola.core.basebindingadapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;

/**
 *
 * @author Medivh
 * @date 2017/12/27
 */

public abstract class BaseMuliteItemAdapter<T extends ItemViewType> extends BaseBindingAdapter<T> {

    private SparseIntArray layouts;

    public static final int TYPE_NOT_FOUND = -404;

    public BaseMuliteItemAdapter(Context context) {
        super(context);
    }

    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(type, layoutResId);
    }
    private int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }
    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater,getLayoutId(viewType),parent,false));
    }
    @Override
    public int getItemViewType(int position) {
        final  T t  = getList().get(position);
        return t.getViewType();
    }

}
