package com.example.pascm033.marvelcharacters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarvelResponse {

    @SerializedName("data")
    private CharacterResponse marvelInfo;

    public CharacterResponse getMarvelInfo() {
        return marvelInfo;
    }
}
