package com.example.syennamani.syncspotify.Web;

import com.example.syennamani.syncspotify.JSON.Albums;
import com.example.syennamani.syncspotify.JSON.JsonBody;
import com.example.syennamani.syncspotify.JSON.Token;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by syennamani on 1/29/2018.
 */

public interface WebService {

    @FormUrlEncoded
    @POST("/api/token")
    Call<Token> getAccessToken(@Header("Authorization") String val,
                               @Field("grant_type") String fieldVal
    );

    @GET("v1/search")
    Observable<JsonBody> getAlbums(@Header("Authorization") String headerVal,
                                   @Query("q") String albumName,
                                   @Query("type") String type
    );
}
