package com.medivh.lattecore.net;

import com.medivh.lattecore.app.ConfigKeys;
import com.medivh.lattecore.app.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    private static final class RetrofitHolder{

        private static final String BASE_URL = (String) Latte.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }
    private static final class OkHttpHolder{

        private static final int TIME_OUT = 60 ;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){

        return  RestServiceHolder.REST_SERVICE;
    }


    public static Retrofit getRetrofit()
    {
        return RetrofitHolder.RETROFIT_CLIENT;
    }


    public static OkHttpClient getOKHttpClient()
        {
            return OkHttpHolder.OK_HTTP_CLIENT;
        }



}
