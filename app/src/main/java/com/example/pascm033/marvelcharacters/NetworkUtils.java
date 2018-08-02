package com.example.pascm033.marvelcharacters;

import android.util.Log;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUtils {

    private static String apiKey = "c62371aa0f963dd954418fdabf344922";
    private static String pKey = "9b360be1902794bf22e66cd6c8f7fb8a4219d6fa";

    /**
     * interface for callbacks
     */

    interface ApiCallListener {
        void onSuccess(MarvelResponse marvelResponse);
        void onFailure(Throwable throwable);
    }

    interface ApiCharacterListener {
        void onSuccess(CharacterInfo characterInfo);
        void onFailure(Throwable throwable);
    }

    /**
     * grabbing all characters without using AsyncTask
     *      ~still working on background thread
     * @param listener
     */

    static void getCharacterInfoAsync(final ApiCallListener listener) {
        OneCharacter oneCharacter = RetrofitUtils.getInstance().create(OneCharacter.class);

            String ts = "1";
            String hash = ts + pKey + apiKey;

            hash = md5(hash);

            oneCharacter
                .getAllCharacters(apiKey, ts, hash)
                .enqueue(new Callback<MarvelResponse>() {   // enqueue asynchronously sends request and notify callback of its response

                    @Override
                    public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                        if (response.isSuccessful()) {
                            listener.onSuccess(response.body());
                        } else {
                            try {
                                Log.e("TAG", "Error with code: " + response.code() + " " + response.errorBody().string());
                            } catch (Exception ex) {}
                        }
                    }

                    @Override
                    public void onFailure(Call<MarvelResponse> call, Throwable t) {
                        listener.onFailure(t);
                    }
                });
    }

    /**
     * Method to perform the search function for only one character
     * @param query
     * @return
     */
    static void getCharacterInfo(String query, final ApiCharacterListener listener) {
        OneCharacter oneCharacter = RetrofitUtils.getInstance().create(OneCharacter.class);

        String ts = "1";
        String hash = ts + pKey + apiKey;

        hash = md5(hash);

        oneCharacter
            .getCharacter(query, apiKey, ts, hash)
            .enqueue(new Callback<MarvelResponse>() {
                @Override
                public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().getMarvelInfo().getCharacterInfoList().get(0));
                    }
                }

                @Override
                public void onFailure(Call<MarvelResponse> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
    }

    /**
     * function to call all marvel characters
     * @return
     */

    static MarvelResponse getAllCharacters() {
        OneCharacter oneCharacter = RetrofitUtils.getInstance().create(OneCharacter.class);

        try {
            String ts = "1";
            String hash = ts + pKey + apiKey;

            hash = md5(hash);

            Response<MarvelResponse> response = oneCharacter
                    .getAllCharacters(apiKey, ts, hash)
                    .execute();

            if (response != null && response.isSuccessful()) {
                return response.body();
            }

            // logging errors
            if (response.code() >= 400 && response.code() < 500) {
                Log.e("TAG", response.code() + " " + response.errorBody().string());
            }

        } catch (IOException ex) {
            Log.e("TAG", "Error in API call");
        }
        return null;
    }

    /**
     * md5 algorithm for hash variable
     *
     * @param s
     * @return
     */
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}


