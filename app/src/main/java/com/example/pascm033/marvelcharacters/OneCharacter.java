package com.example.pascm033.marvelcharacters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OneCharacter {

    @GET("/v1/public/characters")
    Call<CharacterResponse> getCharacter(@Query("q") String query);

}
