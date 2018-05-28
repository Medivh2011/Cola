package com.medivh.latte.core.net;

import android.content.Context;


import com.medivh.core.ui.LoaderStyle;
import com.medivh.latte.core.net.callback.IError;
import com.medivh.latte.core.net.callback.IFailure;
import com.medivh.latte.core.net.callback.IRequest;
import com.medivh.latte.core.net.callback.ISuccess;

import java.io.File;
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

    private RequestBody mBody;

    private Context mContext;

    private LoaderStyle mLoaderStyle;

    private File mFile;

    private String mDownloadDir;

    private String mExtension;

    private String mName;

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder downloadDir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

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
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String path) {
        this.mFile = new File(path);
        return this;
    }

    public final RestClientBuilder loader(Context context)
    {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
    public final RestClient build(){

        return new RestClient(mUrl,mParams,mIRequest,
                              mISuccess,mIFailure,mIError,
                              mBody,mDownloadDir,mExtension,
                              mName,mFile,mContext,mLoaderStyle);
    }




}
