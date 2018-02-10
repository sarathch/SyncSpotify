package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.syennamani.syncspotify.JSON.Items;
import com.example.syennamani.syncspotify.JSON.JsonBody;
import com.example.syennamani.syncspotify.JSON.Token;
import com.example.syennamani.syncspotify.Web.WebClient;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
    
    @Override
    public void fetchAlbums(String albumName) {
        String headerVal = "Bearer "+mAccessToken;
        Log.v("HEADER!!", headerVal);
        lastSearch=albumName;
        new WebClient("Query")
                .getAlbumsToClient(headerVal, albumName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // Fetch the items sub list with in the JsonBody object
                .map(new Function<JsonBody, List<Items> >() {
                    @Override
                    public List<Items> apply(JsonBody jsonBody){
                        List<Items> items = Arrays.asList(jsonBody.getAlbums().getItems());
                        List<Items> filteredList = new ArrayList<>();
                        for(Items item : items){
                            Log.v("ITEM",item.toString());
                            if(item.getAlbum_type().equals("album"))
                                filteredList.add(item);
                        }
                         return filteredList;
                    }
                })
                .subscribe(new Observer<List<Items>>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("Response received", "subscribed");
                    }

                    @Override
                    public void onNext(List<Items> list) {
                        Log.v("Response received", ""+list.size());
                        mAlbumsView.showAlbums(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("Response received", "error");
                        mAlbumsView.showNoAlbums();
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
