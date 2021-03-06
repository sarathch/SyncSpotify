package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syennamani.syncspotify.JSON.Albums;
import com.example.syennamani.syncspotify.JSON.Items;
import com.example.syennamani.syncspotify.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link SpotifyAlbumsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpotifyAlbumsFragment extends Fragment implements SpotifyAlbumsContract.View {


    private SpotifyAlbumsContract.Presenter mAlbumsPresenter;

    private SpotifyAlbumsAdapter mAlbumsAdapter;

    private TextView mNoAlbumsTextView;

    private LinearLayout mNoAlbumsView,mAlbumsView;

    public SpotifyAlbumsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SpotifyAlbumsFragment.
     */
    public static SpotifyAlbumsFragment newInstance() {
        SpotifyAlbumsFragment fragment = new SpotifyAlbumsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlbumsAdapter = new SpotifyAlbumsAdapter(new ArrayList<Items>(0));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_spotify_albums, container, false);

        final EditText edittext = root.findViewById(R.id.et_search);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    mAlbumsPresenter.fetchAlbums(edittext.getText().toString());
                    return true;
                }
                return false;
            }
        });

        final Button btSearch= root.findViewById(R.id.bt_search);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlbumsPresenter.fetchAlbums(edittext.getText().toString());
            }
        });

        // Set up the list view
        ListView listView = root.findViewById(R.id.albums_list);
        listView.setAdapter(mAlbumsAdapter);
        mAlbumsView = root.findViewById(R.id.loadAlbums);

        // Set up no albums view
        mNoAlbumsView = root.findViewById(R.id.noAlbums);
        mNoAlbumsTextView = root.findViewById(R.id.tv_noAlbums);
        return root;
    }


    @Override
    public void setPresenter(SpotifyAlbumsContract.Presenter presenter) {
        mAlbumsPresenter = presenter;
    }

    @Override
    public void showNoAlbums() {
        mAlbumsView.setVisibility(View.GONE);
        mNoAlbumsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAlbums(List<Items> albums) {
        mAlbumsAdapter.replaceData(albums);

        mAlbumsView.setVisibility(View.VISIBLE);
        mNoAlbumsView.setVisibility(View.GONE);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setLoadingIndicator(final boolean state) {
        if (getView() == null) {
            return;
        }
    }

    @Override
    public void showAuthError() {

    }

    @Override
    public void showFetchError() {

    }
}
