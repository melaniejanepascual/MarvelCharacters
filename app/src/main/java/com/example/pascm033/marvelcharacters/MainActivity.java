package com.example.pascm033.marvelcharacters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mCharacterInput;
    TextView mNameText;
    TextView mDescriptionText;
    ImageView mCharacterImg;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterInput = findViewById(R.id.characterInput);
        mNameText = findViewById(R.id.nameText);
        mDescriptionText = findViewById(R.id.descriptionText);
        mCharacterImg = findViewById(R.id.characterImg);
        mRecyclerView = findViewById(R.id.rvMarvel);

        // set a grid layout manager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        NetworkUtils.getCharacterInfoAsync(apiCallListener);
    }

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

    public void searchCharacters(View view) {
        String queryString = mCharacterInput.getText().toString();

        //hiding the keyboard
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
            //search up a specific character
            new FetchCharacter(mNameText, mDescriptionText, mCharacterImg).execute(queryString);
            mDescriptionText.setText("");
            mNameText.setText(R.string.loading);
        }

        else {
            if (queryString.length() == 0) {
                mDescriptionText.setText("");
                mNameText.setText("Please enter a search term");
            } else {
                mDescriptionText.setText("");
                mNameText.setText("Please check your network connection and try again");
            }
        }
    }
}
