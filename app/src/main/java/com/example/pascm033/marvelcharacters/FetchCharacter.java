package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FetchCharacter extends AsyncTask<String, Void, MarvelResponse> {

    private TextView mNameText;
    private TextView mDescriptionText;
    private ImageView mCharacterImg;

    public FetchCharacter (TextView mNameText, TextView mDescriptionText, ImageView mCharacterImg) {
        this.mNameText = mNameText;
        this.mDescriptionText = mDescriptionText;
        this.mCharacterImg = mCharacterImg;
    }

    // method for handling the results on the UI thread
    @Override
    protected void onPostExecute(MarvelResponse characterInfoList) {
        if (characterInfoList != null) {
            CharacterInfo characterInfo = characterInfoList.getMarvelInfo().getCharacterInfoList().get(0);
            mNameText.setText(characterInfo.getName());
            mDescriptionText.setText(characterInfo.getDescription());
            Picasso.get()
                    .load(Uri.parse(characterInfo.getThumbnail().getPath() + "." + characterInfo.getThumbnail().getExtension()))
                    .into(mCharacterImg);
        } else {
            mNameText.setText("Error");
        }
    }

    @Override
    protected MarvelResponse doInBackground(String... params) {
        return NetworkUtils.getCharacterInfo(params[0]);
    }
}

