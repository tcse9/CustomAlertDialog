package com.ovi.customalertdialog.retrofit;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Extreme_Piash on 1/4/2017.
 */

public class ApiClient {
    private static Retrofit sRetrofit;

    private ApiClient() {
    }

    public synchronized static Retrofit getInstance(final Context context) {
        if (sRetrofit == null) {
            createRetrofit(context);
        }
        return sRetrofit;
    }


    private static void createRetrofit(final Context context) {
       /* OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                .build();*/

        /*OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder().readTimeout(50, TimeUnit.SECONDS);*/

        /*builder.addInterceptor(new AddCookiesInterceptor(context)); // VERY VERY IMPORTANT
        builder.addInterceptor(new ReceivedCookiesInterceptor(context)); // VERY VERY IMPORTANT
        client = builder.build();*/

        /*OkHttpClient client2 = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .cookieJar(new JavaNetCookieJar(new CookieManager()))
                .build();*/



        CookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(50, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .build();


        sRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.champs21.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
