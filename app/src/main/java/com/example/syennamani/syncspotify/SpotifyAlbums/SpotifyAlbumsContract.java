package com.example.syennamani.syncspotify.SpotifyAlbums;

import com.example.syennamani.syncspotify.BasePresenter;
import com.example.syennamani.syncspotify.BaseView;
import com.example.syennamani.syncspotify.JSON.Albums;
import com.example.syennamani.syncspotify.JSON.Items;

import java.util.List;

/**
 * Created by syennamani on 2/7/2018.
 */

public interface SpotifyAlbumsContract {

    interface View extends BaseView<Presenter> {

        void showNoAlbums();

        void showAlbums(List<Items> items);

        boolean isActive();

        void setLoadingIndicator(boolean state);

        void showAuthError();

        void showFetchError();
    }

    interface Presenter extends BasePresenter<View> {

        void fetchAccessToken();

        void fetchAlbums(String albumName);

    }
}
