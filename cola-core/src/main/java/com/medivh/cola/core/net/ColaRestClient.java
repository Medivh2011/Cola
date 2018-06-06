package com.medivh.cola.core.net;

import android.content.Context;



import com.medivh.cola.core.net.callback.IError;
import com.medivh.cola.core.net.callback.IFailure;
import com.medivh.cola.core.net.callback.IRequest;
import com.medivh.cola.core.net.callback.ISuccess;
import com.medivh.cola.core.net.callback.RequestCallBacks;
import com.medivh.cola.core.net.download.DownloadHandler;
import com.medivh.cola.core.ui.loader.LatteLoader;
import com.medivh.cola.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


public class ColaRestClient {

    private final String URL;

    private final Map<String,Object> PARAMS;

    private final IRequest IREQUEST;

    private final ISuccess ISUCCESS;

    private final IFailure IFAILURE;

    private final IError IERROR;

    private final RequestBody BODY;

    private final Context CONTEXT;

    private final LoaderStyle LOADER_STYLE;

    private final File FILE;

    private final String DOWNLOAD_DIR;

    private final String EXTENSION;

    private final String NAME;

    public ColaRestClient(String url,
                          Map<String, Object> params,
                          IRequest iRequest,
                          ISuccess iSuccess,
                          IFailure iFailure,
                          IError iError,
                          RequestBody body,
                          String downloadDir,
                          String extension,
                          String name,
                          File file,
                          Context context,
                          LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;

    }

    public static  RestClientBuilder builder()
   {
       return new RestClientBuilder();
   }

   private void request(HttpMethod method)
   {
        final ColaRestService restService = ColaRestCreator.getRestService();

        Call<String> call = null;

        if ( null != IREQUEST )
        {
            IREQUEST.onRequestStart();
        }
        if ( LOADER_STYLE != null )
        {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method)
        {
            case GET:
                call = restService.get(URL,PARAMS);
                break;
            case POST:
                call = restService.post(URL,PARAMS);
                break;
            case PUT:
                call = restService.put(URL,PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = restService.upload(URL, body);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL,BODY);
                break;
            case POST_RAW:
                call = restService.postRaw(URL,BODY);
                break;

            default:
                break;

        }
        if ( null != call)
        {
            call.enqueue(getRequestCallback());
        }



   }

   private Callback<String> getRequestCallback()
   {
       return new RequestCallBacks(IREQUEST,ISUCCESS,IFAILURE,IERROR,LOADER_STYLE);
   }

   public final void get()
   {
       request(HttpMethod.GET);
   }
    public final void post()
    {
        if ( null == BODY)
        {
            request(HttpMethod.POST);
        }else {
            if ( null != PARAMS)
            {
                throw new RuntimeException("if you want to post raw ,the params must be empty");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put()
    {
        if ( null == BODY)
        {
            request(HttpMethod.PUT);
        }else {
            if ( null != PARAMS)
            {
                throw new RuntimeException("if you want to put raw, the params must be empty");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete()
    {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL,PARAMS ,IREQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                ISUCCESS, IFAILURE, IERROR)
                .handleDownload();
    }



}
