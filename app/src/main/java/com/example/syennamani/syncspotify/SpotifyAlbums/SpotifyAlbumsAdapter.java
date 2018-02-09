package com.example.syennamani.syncspotify.SpotifyAlbums;

import android.content.ClipData;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.syennamani.syncspotify.JSON.Items;
import com.example.syennamani.syncspotify.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by syennamani on 2/7/2018.
 */

public class SpotifyAlbumsAdapter extends BaseAdapter {

    private List<Items> mItems;

    public SpotifyAlbumsAdapter(List<Items> items){
        setList(items);
    }

    public void replaceData(List<Items> items) {
        setList(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Items getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rowView = inflater.inflate(R.layout.adapter_album_item, viewGroup, false);
        }

        final Items item = getItem(i);

        TextView tvAlbumName = rowView.findViewById(R.id.tv_name);
        ImageView ivAlbumCover = rowView.findViewById(R.id.iv_album);

        tvAlbumName.setText(item.getName());
        if(item.getImages()!= null && item.getImages()[0] != null && item.getImages()[0].getUrl()!=null){
            Picasso.with(viewGroup.getContext()).load(item.getImages()[0].getUrl()).into(ivAlbumCover);
        }
        return rowView;
    }

    public void setList(List<Items> list) {
        mItems = list;
    }
}
