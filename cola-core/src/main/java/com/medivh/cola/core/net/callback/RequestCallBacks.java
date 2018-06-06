package com.medivh.cola.core.net.callback;

import android.os.Handler;


import com.medivh.cola.core.app.Cola;
import com.medivh.cola.core.app.ConfigKeys;
import com.medivh.cola.core.ui.loader.LatteLoader;
import com.medivh.cola.core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFailure mIFailure;

    private IError mIError;

    private LoaderStyle mLoaderStyle;

    private static Handler mHandler = Cola.getHandler();


    public RequestCallBacks(IRequest IRequest, ISuccess ISuccess, IFailure IFailure, IError IError ,LoaderStyle style) {
        mIRequest = IRequest;
        mISuccess = ISuccess;
        mIFailure = IFailure;
        mIError = IError;
        mLoaderStyle = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful())
        {
            if (call.isExecuted())
            {
                if ( null != mISuccess)
                {
                    mISuccess.onSuccess(response.body());
                }
            }
        }else {
            if ( null != mIError)
            {
                mIError.onError(response.code(), response.message());
            }
        }
       onRequestFinish();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if ( null != mIFailure)
        {
            mIFailure.onFailure();
        }
        if (null != mIRequest)
        {
            mIRequest.onRequestEnd();
        }
       onRequestFinish();

    }

    private void onRequestFinish() {
        final long delayed = Cola.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (mLoaderStyle != null) {
            mHandler.postDelayed(() -> LatteLoader.stopLoading(), delayed);
        }
    }
}
