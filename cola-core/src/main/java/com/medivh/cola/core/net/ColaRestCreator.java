package com.medivh.cola.core.net;


import android.text.TextUtils;

import com.medivh.cola.core.app.ConfigKeys;
import com.medivh.cola.core.app.Cola;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ColaRestCreator {

    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Cola.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = getRetrofitClient();

        private static final Retrofit getRetrofitClient() {

            final Retrofit.Builder builder = new Retrofit.Builder();

            builder.addConverterFactory(ScalarsConverterFactory.create())
                    .client(OkHttpHolder.OK_HTTP_CLIENT);
            if (!TextUtils.isEmpty(BASE_URL)) {
                builder.baseUrl(BASE_URL);
            }
            return builder.build();
        }

    }

    private static final class OkHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final ColaRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(ColaRestService.class);
    }

    public static ColaRestService getRestService() {

        return RestServiceHolder.REST_SERVICE;
    }


    public static Retrofit getRetrofit() {
        return RetrofitHolder.RETROFIT_CLIENT;
    }


    public static OkHttpClient getOKHttpClient() {
        return OkHttpHolder.OK_HTTP_CLIENT;
    }


}
