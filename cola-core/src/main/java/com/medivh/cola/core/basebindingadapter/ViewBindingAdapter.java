package com.medivh.cola.core.basebindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.medivh.imageloader.GlideApp;
/**
 *
 * @author Medivh
 * @date 2017/12/28
 */

public class ViewBindingAdapter
{

    @BindingAdapter({"imageUrl" , "placeholder","error"})
    public static void loadImageFromUrl(ImageView imageView , String url , final Drawable placeholder, final Drawable error)
    {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImageUrl(ImageView imageView , String url)
    {
        GlideApp.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

}
