package com.example.pascm033.marvelcharacters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mBookInput;
    TextView mTitleText;
    TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mTitleText = (TextView) findViewById(R.id.titleText);
        mAuthorText = (TextView) findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        //hiding the keyvoard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //checking network connection
        ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //correct case
        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        }

        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText("Please enter a search term");
            } else {
                mAuthorText.setText("");
                mTitleText.setText("Please check your network connection and try again");
            }
        }
    }

    public static class FetchBook extends AsyncTask<String, Void, VolumesResponse> {

        private TextView mTitleText;
        private TextView mAuthorText;

        public FetchBook (TextView mTitleText, TextView mAuthorText) {
            this.mTitleText = mTitleText;
            this.mAuthorText = mAuthorText;

        }

        // method for handling the results on the UI thread
        @Override
        protected void onPostExecute(VolumesResponse volumeInfoList) {
//            try{
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray itemsArray = jsonObject.getJSONArray("items");
//
//                for (int i = 0; i < itemsArray.length(); i++) {
//                    JSONObject book = itemsArray.getJSONObject(i);
//                    String title = null;
//                    String authors = null;
//                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");
//
//                    try {
//                        title = volumeInfo.getString("title");
//                        authors = volumeInfo.getString("authors");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    if(title != null && authors != null) {
//                        mTitleText.setText(title);
//                        mAuthorText.setText(authors);
//                        return;
//                    }
//                }
//                mTitleText.setText("No Results Found");
//                mAuthorText.setText("");
//            } catch (Exception e) {
//                mTitleText.setText("No Results Found");
//                mAuthorText.setText("");
//                e.printStackTrace();
//            }

            VolumeInfo volumeInfo = volumeInfoList.getVolumeInfoList().get(0).getVolumeInfo();
            mTitleText.setText(volumeInfo.getTitle());
            mAuthorText.setText(volumeInfo.getAuthor().get(0));
        }
        @Override
        protected VolumesResponse doInBackground(String... params) {
//            return NetworkUtils.getBookInfo(params[0]);
            return NetworkUtils.getBookInfo(params[0], 10, "books");
        }
    }
}
