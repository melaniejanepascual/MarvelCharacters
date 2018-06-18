package com.example.pascm033.marvelcharacters;

import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText mCharacterInput;
    TextView mNameText;
    TextView mDescriptionText;
    ImageView mCharacterImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharacterInput = (EditText) findViewById(R.id.characterInput);
        mNameText = (TextView) findViewById(R.id.nameText);
        mDescriptionText = (TextView) findViewById(R.id.descriptionText);
        mCharacterImg = findViewById(R.id.characterImg);
    }

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
