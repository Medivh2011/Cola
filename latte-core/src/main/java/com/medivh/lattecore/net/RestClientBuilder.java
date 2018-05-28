package com.medivh.lattecore.net;

import android.content.Context;

import com.medivh.lattecore.net.callback.IError;
import com.medivh.lattecore.net.callback.IFailure;
import com.medivh.lattecore.net.callback.IRequest;
import com.medivh.lattecore.net.callback.ISuccess;
import com.medivh.lattecore.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    private  String mUrl;

    private Map<String,Object> mParams;

    private IRequest mIRequest;

    private ISuccess mISuccess;

    private IFailure mIFailure;

    private IError mIError;

    private RequestBody mRequestBody;

    private Context mContext;

    private LoaderStyle mLoaderStyle;


    public final RestClientBuilder url(String url)
    {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String,Object> params)
    {
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key , Object value)
    {
        if (mParams == null)
        {
            mParams = new WeakHashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public final RestClientBuilder params(String key , String value)
    {
        if (mParams == null)
        {
            mParams = new WeakHashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest)
    {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder error(IError iError)
    {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure)
    {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess)
    {
        this.mISuccess = iSuccess;
        return this;
    }


    public final RestClientBuilder raw(String raw)
    {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json,charset=UTF-8"),raw);
        return this;
    }

    private Map<String,Object> checkParams(){
        if ( null == mParams)
        {
            return new WeakHashMap<>();
        }
        return mParams;
    }

    public final RestClientBuilder loader(Context context ,LoaderStyle loaderStyle)
    {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context)
    {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build(){

        return new RestClient(mUrl,mParams,mIRequest,mISuccess,mIFailure,mIError,mRequestBody,mContext,mLoaderStyle);
    }




}
