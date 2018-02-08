package com.example.syennamani.syncspotify;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by syennamani on 2/7/2018.
 */

public class SpotifyAlbumsPresenter implements SpotifyAlbumsContract.Presenter{

    private final SpotifyAlbumsContract.View mTasksView;
    private static final String REDIRECT_URI = "syncspotify://callback";
    private static final String CLIENT_ID = "cf3e4b0d0b61406f845a133343147592";
    private static final String RESPONSE_TYPE = "code";

    public SpotifyAlbumsPresenter(@NonNull SpotifyAlbumsContract.View tasksView){
        mTasksView = tasksView;
        mTasksView.setPresenter(this);
    }

    @Override
    public void fetchAccessToken() {
        String headerVal = "Basic Y2YzZTRiMGQwYjYxNDA2Zjg0NWExMzMzNDMxNDc1OTI6ZDk1ODBlMDQ4MDUxNGFhMDhlMWZlOWFlMmRiNTI3NWI=";
        Map<String, String> map = new HashMap<>();
        map.put("grant_type","client_credentials");
        Call<JsonObject> call = WebClient.getInstance().getAccessTokenToClient(headerVal, "client_credentials");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("Response", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.i("onSuccess", response.body().toString());
                    }else{
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.v("Response","Failed+"+t.getMessage());
            }
        });
    }

    @Override
    public void fetchAlbums(String albumName) {

    }

    @Override
    public void start() {
        fetchAccessToken();
    }

    private void loadAlbums() {

    }
}
