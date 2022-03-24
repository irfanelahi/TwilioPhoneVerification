package com.irfandev.twiliointegration.Backend;


import com.irfandev.twiliointegration.Backend.ServerResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 *Irfan Elahi Dev
 */
public interface RetroService {
    @POST("/api/Login")
    @FormUrlEncoded
    Call<ServerResponse> sendVerificationCode(@FieldMap Map<String, String> params
    );
}