package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

public class FetchCharacter extends AsyncTask<String, Void, CharacterResponse> {

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
    protected void onPostExecute(CharacterResponse characterInfoList) {
        CharacterInfo characterInfo = characterInfoList.getCharacterInfoList().get(0);
        mNameText.setText(characterInfo.getName());
        mDescriptionText.setText(characterInfo.getDescription());
        mCharacterImg.setImageURI(Uri.parse(characterInfo.getThumbnail().getPath() + "." + characterInfo.getThumbnail().getExtension()));
    }

    @Override
    protected CharacterResponse doInBackground(String... params) {
        return NetworkUtils.getCharacterInfo(params[0]);
    }
}
