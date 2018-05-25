package com.medivh.lattecore.net.callback;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallBacks implements Callback<String> {

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFailure mIFailure;

    private IError mIError;


    public RequestCallBacks(IRequest IRequest, ISuccess ISuccess, IFailure IFailure, IError IError) {
        mIRequest = IRequest;
        mISuccess = ISuccess;
        mIFailure = IFailure;
        mIError = IError;
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


    }
}
