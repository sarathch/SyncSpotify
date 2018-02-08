package com.example.syennamani.syncspotify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

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
        }

        // Create the presenter
        mAlbumsPresenter = new SpotifyAlbumsPresenter(albumsFragment);
        mAlbumsPresenter.start();
/*        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://accounts.spotify.com/authorize/"  + "?client_id=" + CLIENT_ID +"response_type=" + RESPONSE_TYPE + "&redirect_uri=" + REDIRECT_URI));
        startActivity(intent);*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if(uri!=null)
            Log.v("RESPONSE", uri.toString());
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                // we'll do that in a minute
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
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
