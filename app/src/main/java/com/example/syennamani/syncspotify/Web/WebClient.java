package com.example.syennamani.syncspotify.Web;

import com.example.syennamani.syncspotify.JSON.Albums;
import com.example.syennamani.syncspotify.JSON.JsonBody;
import com.example.syennamani.syncspotify.JSON.Token;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by syennamani on 1/29/2018.
 */

public class WebClient {
    private static final String SPOTIFY_OAUTH_URL = "https://accounts.spotify.com/";
    private static final String SPOTIFY_SEARCH_URL = "https://api.spotify.com/";
    private static WebClient instance;
    private WebService webService;

    public WebClient(String action) {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        if(action.equals("Auth")) {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SPOTIFY_OAUTH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webService = retrofit.create(WebService.class);
        }else{
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SPOTIFY_SEARCH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webService = retrofit.create(WebService.class);
        }
    }

    public Call<Token> getAccessTokenToClient(String headerVal) {
        return webService.getAccessToken(headerVal, "client_credentials");
    }

    public Call<JsonBody> getAlbumsToClient(String headerVal, String albumName) {
        return  webService.getAlbums(headerVal,albumName, "album");
    }
}