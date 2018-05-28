package com.medivh.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.medivh.common.databinding.SimpleDelegateBinding;
import com.medivh.lattecore.app.Latte;
import com.medivh.lattecore.delegates.LatteDelegate;
import com.medivh.lattecore.net.RestClient;
import com.medivh.lattecore.ui.LoaderStyle;

public class SimpleDelegate extends LatteDelegate<SimpleDelegateBinding>{

    @Override
    protected int getLayoutId() {
        return R.layout.simple_delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void init() {
        getBaiduInfos();
    }

    private void getBaiduInfos(){

        RestClient.builder()
                .params("","")
                .success((response) -> mBinding.tvShow.setText(response))
                .error((code, msg) -> Toast.makeText(Latte.getApplicationContext(),msg + code,Toast.LENGTH_LONG).show())
                .failure(()-> mBinding.tvShow.setText("onFailure"))
                .loader(getContext(), LoaderStyle.LineSpinFadeLoaderIndicator)
                .url("https://www.baidu.com/")
                .build().get();
    }
}
