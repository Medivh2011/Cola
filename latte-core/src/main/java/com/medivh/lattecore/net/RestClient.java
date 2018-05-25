package com.medivh.lattecore.net;

import com.medivh.lattecore.net.callback.IError;
import com.medivh.lattecore.net.callback.IFailure;
import com.medivh.lattecore.net.callback.IRequest;
import com.medivh.lattecore.net.callback.ISuccess;
import com.medivh.lattecore.net.callback.RequestCallBacks;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;

    private final Map<String,Object> PARAMS;

    private final IRequest IREQUEST;

    private final ISuccess ISUCCESS;

    private final IFailure IFAILURE;

    private final IError IERROR;

    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      RequestBody body) {
        this.URL = url;
        this.PARAMS = params;
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BODY = body;
    }

    public static  RestClientBuilder builder()
   {
       return new RestClientBuilder();
   }

   private void request(HttpMethod method)
   {
        final RestService restService = RestCreator.getRestService();

        Call<String> call = null;

        if ( null != IREQUEST )
        {
            IREQUEST.onRequestStart();
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

                break;
            case PUT_RAW:

                break;
            case POST_RAW:

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
       return new RequestCallBacks(IREQUEST,ISUCCESS,IFAILURE,IERROR);
   }

   public final void get()
   {
       request(HttpMethod.GET);
   }
    public final void post()
    {
        request(HttpMethod.POST);
    }

    public final void put()
    {
        request(HttpMethod.PUT);
    }

    public final void delete()
    {
        request(HttpMethod.DELETE);
    }




}
