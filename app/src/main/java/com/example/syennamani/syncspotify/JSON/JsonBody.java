package com.example.syennamani.syncspotify.JSON;

/**
 * Created by syennamani on 2/8/2018.
 */

public class JsonBody {

    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Albums = "+albums+"]";
    }
}
