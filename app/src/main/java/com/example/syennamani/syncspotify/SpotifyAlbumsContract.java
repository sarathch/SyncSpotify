package com.example.syennamani.syncspotify;

/**
 * Created by syennamani on 2/7/2018.
 */

public interface SpotifyAlbumsContract {

    interface View {

        void showNoAlbums();

        //void showAlbums(List<Album> albums);

        boolean isActive();

        void setLoadingIndicator(boolean state);
    }

    interface Presenter {

        void fetchAccessToken();

        void fetchAlbums(String albumName);

    }
}
