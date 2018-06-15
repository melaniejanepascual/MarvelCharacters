package com.example.pascm033.marvelcharacters;

import android.os.AsyncTask;
import android.widget.TextView;

public class FetchBook extends AsyncTask <String, Void, String> {
    private TextView mTitleText;
    private TextView mAuthorText;

    //constructor
    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
