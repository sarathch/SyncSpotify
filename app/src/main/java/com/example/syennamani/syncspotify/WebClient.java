package com.example.syennamani.syncspotify;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by syennamani on 1/29/2018.
 */

public class WebClient {
    private static final String SPOTIFY_OAUTH_URL = "https://accounts.spotify.com/";

    private static WebClient instance;
    private WebService webService;

    private WebClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SPOTIFY_OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        webService = retrofit.create(WebService.class);
    }

    public static WebClient getInstance() {
        if (instance == null) {
            instance = new WebClient();
        }
        return instance;
    }

}