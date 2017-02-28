package com.ovi.customalertdialog.retrofit;

import com.ovi.customalertdialog.model.ModelBase;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Extreme_Piash on 1/4/2017.
 */

public interface NetworkCallInterface {

    @FormUrlEncoded
    @POST("user/auth")
    Call<ModelBase> auth(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("calender/getbatch")
    Call<ModelBase> getBatch(@Field("user_secret") String user_secret);




}
