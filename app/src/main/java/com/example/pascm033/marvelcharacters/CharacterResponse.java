package com.example.pascm033.marvelcharacters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResponse {

    @SerializedName("results")
    private List<CharacterInfo> characterInfoList;

    public List<CharacterInfo> getCharacterInfoList() {
        return characterInfoList;
    }
}
