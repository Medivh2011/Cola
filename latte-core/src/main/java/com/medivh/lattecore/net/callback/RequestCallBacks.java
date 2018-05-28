package com.medivh.lattecore.net.callback;

import android.os.Handler;
import android.os.Looper;

import com.medivh.lattecore.app.ConfigKeys;
import com.medivh.lattecore.app.Latte;
import com.medivh.lattecore.ui.LatteLoader;
import com.medivh.lattecore.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFailure mIFailure;

    private IError mIError;

    private LoaderStyle mLoaderStyle;

    private static Handler mHandler = Latte.getHandler();


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
        final long delayed = Latte.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (mLoaderStyle != null) {
            mHandler.postDelayed(() -> LatteLoader.stopLoading(), delayed);
        }
    }
}
