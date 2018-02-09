package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.syennamani.syncspotify.R;

public class SpotifyAlbumsActivity extends AppCompatActivity {

    private SpotifyAlbumsPresenter mAlbumsPresenter;
    private WebView webView;
    private static final String REDIRECT_URI = "syncspotify://callback";
    private static final String CLIENT_ID = "cf3e4b0d0b61406f845a133343147592";
    private static final String RESPONSE_TYPE = "code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Attach the fragment view
        SpotifyAlbumsFragment albumsFragment = (SpotifyAlbumsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(albumsFragment == null){
            albumsFragment = SpotifyAlbumsFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, albumsFragment);
            transaction.commit();
        }

        // Create the presenter
        mAlbumsPresenter = new SpotifyAlbumsPresenter(albumsFragment);
        mAlbumsPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
