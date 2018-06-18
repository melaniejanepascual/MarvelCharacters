package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Response;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private String apiKey = "c62371aa0f963dd954418fdabf344922";
    private String pKey = "9b360be1902794bf22e66cd6c8f7fb8a4219d6fa";
    private static final String CHARACTER_BASE_URL = "http(s)://gateway.marvel.com/";

    static CharacterResponse getCharacterInfo(String query) {
        OneCharacter oneCharacter = RetrofitUtils.getInstance().create(OneCharacter.class);
        try {
            Response<CharacterResponse> response = oneCharacter.getCharacter(query)
                    .execute();

            if (response != null) {
                return response.body();
            }

        } catch (IOException ex) {
            Log.e("TAG", "Error in API call");
        }
        return null;
    }



}
