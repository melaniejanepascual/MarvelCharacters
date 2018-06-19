package com.example.pascm033.marvelcharacters;

/**
 * get all characters to display for the gridlayout
 * @return
 */

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FetchAllCharacters extends AsyncTask<String, Void, MarvelResponse> {

    private MarvelCharacterAdapter adapter;

    // set MarvelCharacterAdapter to the image
    public FetchAllCharacters (MarvelCharacterAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(MarvelResponse characterInfoList) {
        List<CharacterInfo> characterInfo = characterInfoList.getMarvelInfo().getCharacterInfoList();
        adapter.setCharacterInfoList(characterInfo);
    }

    @Override
    protected MarvelResponse doInBackground(String... params) {
        return NetworkUtils.getAllCharacters();
    }
}