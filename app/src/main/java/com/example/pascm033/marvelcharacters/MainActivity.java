package com.example.pascm033.marvelcharacters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText mCharacterInput;
    RecyclerView mRecyclerView;
    ProgressBar mLoading;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterInput = findViewById(R.id.characterInput);
        mRecyclerView = findViewById(R.id.rvMarvel);
        mLoading = findViewById(R.id.loading);
        mToolbar = findViewById(R.id.marvelToolbar);

        setSupportActionBar(mToolbar);

        // setting up the grid layout for portrait and landscape orientation
        if(Configuration.ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
            int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, gridColumnCount);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            int gridColumnCountLandscape = getResources().getInteger(R.integer.grid_column_count_landscape);
            GridLayoutManager gridLandscapeLayoutManager = new GridLayoutManager(this, gridColumnCountLandscape);
            mRecyclerView.setLayoutManager(gridLandscapeLayoutManager);
        }

        NetworkUtils.getCharacterInfoAsync(apiCallListener);

    }

    public void setLoading(boolean loading) {
        if (loading) {
            mLoading.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
        }
    }

    public void characterSearch(View view) {
        String queryString = mCharacterInput.getText().toString();  // convert query to string
        setLoading(true);

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            NetworkUtils.getCharacterInfo(queryString, apiCharacterListener);   // api call for character search
            setLoading(false);
        }
    }

    // TODO: What does this do
    private NetworkUtils.ApiCharacterListener apiCharacterListener = new NetworkUtils.ApiCharacterListener() {
        @Override
        public void onSuccess(CharacterInfo characterInfo) {
            // calling the second activity
            Intent intent  = new Intent(MainActivity.this, SingleCharacterInformationActivity.class);
            intent.putExtra(SingleCharacterInformationActivity.EXTRA_NAME, characterInfo.getName());
            intent.putExtra(SingleCharacterInformationActivity.EXTRA_DESC, characterInfo.getDescription());
            intent.putExtra(SingleCharacterInformationActivity.EXTRA_IMGURL,
                    characterInfo.getThumbnail().getPath() + "." + characterInfo.getThumbnail().getExtension());
            startActivity(intent);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

    private NetworkUtils.ApiCallListener apiCallListener = new NetworkUtils.ApiCallListener() {
        @Override
        public void onSuccess(MarvelResponse marvelResponse) {
            MarvelCharacterAdapter adapter = new MarvelCharacterAdapter(marvelResponse.getMarvelInfo().getCharacterInfoList());
            mRecyclerView.setAdapter(adapter);
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
    };

}
