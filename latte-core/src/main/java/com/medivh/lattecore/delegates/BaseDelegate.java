package com.medivh.lattecore.delegates;


import android.support.annotation.LayoutRes;


import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseDelegate  extends SwipeBackFragment {



    protected abstract  @LayoutRes int getLayoutId();


}
