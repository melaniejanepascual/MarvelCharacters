package com.example.pascm033.marvelcharacters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface OneCharacter {

    @GET("/v1/public/characters")
    Call<MarvelResponse> getCharacter(@Query("name") String query, @Query("apikey") String apiKey,
                                      @Query("ts") String timeStamp, @Query("hash") String hash);

    @GET("/v1/public/characters")
    Call<MarvelResponse> getAllCharacters(@Query("apikey") String apiKey, @Query("ts") String timeStamp,
                                          @Query("hash") String hash);
}
