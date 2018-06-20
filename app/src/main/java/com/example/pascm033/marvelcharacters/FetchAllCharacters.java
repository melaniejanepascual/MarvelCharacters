package com.example.pascm033.marvelcharacters;

/**
 * get all characters to display for the gridlayout
 * @return
 */

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FetchAllCharacters extends AsyncTask<String, Void, MarvelResponse> {

    private MarvelCharacterAdapter adapter;
    private RecyclerView recyclerView;


    // set MarvelCharacterAdapter to the image
    public FetchAllCharacters (RecyclerView recyclerView) {
        //this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPostExecute(MarvelResponse characterInfoList) {
        List<CharacterInfo> characterInfo = characterInfoList.getMarvelInfo().getCharacterInfoList();
        adapter = new MarvelCharacterAdapter(characterInfo);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected MarvelResponse doInBackground(String... params) {
        return NetworkUtils.getAllCharacters();
    }
}