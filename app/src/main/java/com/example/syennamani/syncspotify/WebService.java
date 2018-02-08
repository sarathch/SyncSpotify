package com.example.syennamani.syncspotify;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by syennamani on 1/29/2018.
 */

public interface WebService {
    @FormUrlEncoded
    @POST("/api/token")
    Call<JsonObject> getAccessToken(@Header("Authorization") String val,
                                    @Field("grant_type") String fieldVal
    );

}
