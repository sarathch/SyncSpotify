package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.syennamani.syncspotify.JSON.Items;
import com.example.syennamani.syncspotify.JSON.JsonBody;
import com.example.syennamani.syncspotify.JSON.Token;
import com.example.syennamani.syncspotify.Web.WebClient;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by syennamani on 2/7/2018.
 */

public class SpotifyAlbumsPresenter implements SpotifyAlbumsContract.Presenter{

    private final SpotifyAlbumsContract.View mAlbumsView;
    private static final String CLIENT_HASH = "Y2YzZTRiMGQwYjYxNDA2Zjg0NWExMzMzNDMxNDc1OTI6ZDk1ODBlMDQ4MDUxNGFhMDhlMWZlOWFlMmRiNTI3NWI=";
    private static String mAccessToken = "";
    private static boolean retryOnTokenExpiry = false;
    private String lastSearch = "";
    public SpotifyAlbumsPresenter(@NonNull SpotifyAlbumsContract.View albumsView){
        mAlbumsView = albumsView;
        mAlbumsView.setPresenter(this);
    }

    @Override
    public void fetchAccessToken() {
        String headerVal = "Basic "+CLIENT_HASH;
        Call<Token> call = new WebClient("Auth").getAccessTokenToClient(headerVal);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.i("Response", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Token tokenResponse = response.body();
                        String token = tokenResponse.getmAccessToken();
                        Log.i("onSuccess", token!=null?token:"Access token NULL");
                        mAccessToken = token;
                        if (retryOnTokenExpiry = true && !lastSearch.isEmpty()) {
                            fetchAlbums(lastSearch);
                            retryOnTokenExpiry = false;
                        }
                    }else{
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                        mAlbumsView.showAuthError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.v("Response","Failed+"+t.getMessage());
                mAlbumsView.showAuthError();
            }
        });
    }

/*    @Override
    public void fetchAlbums(String albumName) {
        String headerVal = "Bearer "+mAccessToken;
        Log.v("HEADER!!", headerVal);
        lastSearch=albumName;
        Call<JsonBody> call = new WebClient("Query").getAlbumsToClient(headerVal, albumName);
        call.enqueue(new Callback<JsonBody>() {
            @Override
            public void onResponse(Call<JsonBody> call, Response<JsonBody> response) {

                //Toast.makeText()
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.i("onSuccess", response.body().toString());
                        JsonBody body = response.body();
                        List<Items> items = Arrays.asList(body.getAlbums().getItems());
                        mAlbumsView.showAlbums(items);
                    }else{
                        Log.i("onEmptyResponse", "Returned empty response");
                        mAlbumsView.showNoAlbums();
                    }
                }else {
                    if (response.code() == 401 || response.body().toString().contains("unauthorized")){
                        Log.i("Response", "Access token expired");
                        retryOnTokenExpiry = true;
                        fetchAccessToken();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonBody> call, Throwable t) {
                Log.v("Response","Failed+"+t.getMessage());
                mAlbumsView.showFetchError();
            }
        });
    }*/

    @Override
    public void fetchAlbums(String albumName) {
        String headerVal = "Bearer "+mAccessToken;
        Log.v("HEADER!!", headerVal);
        lastSearch=albumName;
        new WebClient("Query")
                .getAlbumsToClient(headerVal, albumName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBody>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("Response received", "subscribed");
                    }

                    @Override
                    public void onNext(JsonBody jsonBody) {
                        Log.v("Response received", jsonBody.toString());
                        mAlbumsView.showAlbums(Arrays.asList(jsonBody.getAlbums().getItems()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("Response received", "error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void start() {
        fetchAccessToken();
    }

}
