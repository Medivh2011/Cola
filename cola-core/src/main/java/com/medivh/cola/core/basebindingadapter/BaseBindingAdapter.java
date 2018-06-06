package com.medivh.cola.core.basebindingadapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Medivh
 * @date 2017/12/27
 */

public abstract class BaseBindingAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

    protected final LayoutInflater mLayoutInflater;

    private List<T> mList;

    public BaseBindingAdapter(Context context) {
        super();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
    }
    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final T t = mList.get(position);
        setItemVariable(holder.getBinding(), t);
        holder.getBinding().executePendingBindings();
        setItemClickEvent(holder, t);
    }
    protected abstract void setItemVariable(ViewDataBinding binding, T t);

    protected void setItemClickEvent(BindingViewHolder holder, T t) {
    }
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void add(int index, T t) {
        if (index > mList.size()) {
            return;
        }
        mList.add(index, t);
        notifyItemInserted(index);
    }

    public void add(T t) {
        mList.add(t);
    }

    public void addAtFirst(T t) {
        mList.add(0, t);
    }

    public void addAll(List<T> tList) {
        mList.addAll(tList);
    }

    public void remove(int index) {
        if (index >= mList.size()) {
            return;
        }
        mList.remove(index);
        notifyDataSetChanged();
    }

    public void removeFirst() {
        if (mList.size() == 0) {
            return;
        }
        mList.remove(0);
    }

    public void removeLast()
    {
        mList.remove(mList.size() - 1);
    }


    public  void  removeAll()
    {
        mList.clear();
    }

    public List<T> getList() {
        return mList;
    }

}
