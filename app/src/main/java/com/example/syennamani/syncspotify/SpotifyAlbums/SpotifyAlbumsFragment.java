package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.syennamani.syncspotify.JSON.Albums;
import com.example.syennamani.syncspotify.JSON.Items;
import com.example.syennamani.syncspotify.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpotifyAlbumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpotifyAlbumsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpotifyAlbumsFragment extends Fragment implements SpotifyAlbumsContract.View {


    private SpotifyAlbumsContract.Presenter mAlbumsPresenter;

    private SpotifyAlbumsAdapter mAlbumsAdapter;

    //private TextView mNoAlbumsAddView;

    //private LinearLayout mAlbumsView;

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
        mAlbumsAdapter = new SpotifyAlbumsAdapter();
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

        // Set up the list view
        ListView listView = root.findViewById(R.id.albums_list);
        listView.setAdapter(mAlbumsAdapter);
        

        return root;
    }


    @Override
    public void setPresenter(SpotifyAlbumsContract.Presenter presenter) {
        mAlbumsPresenter = presenter;
    }

    @Override
    public void showNoAlbums() {

    }

    @Override
    public void showAlbums(List<Items> albums) {

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
