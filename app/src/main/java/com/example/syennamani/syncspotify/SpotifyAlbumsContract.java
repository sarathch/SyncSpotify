package com.example.syennamani.syncspotify;

/**
 * Created by syennamani on 2/7/2018.
 */

public interface SpotifyAlbumsContract {

    interface View extends BaseView<Presenter> {

        void showNoAlbums();

        //void showAlbums(List<Album> albums);

        boolean isActive();

        void setLoadingIndicator(boolean state);
    }

    interface Presenter extends BasePresenter<View> {

        void fetchAccessToken();

        void fetchAlbums(String albumName);

    }
}
