package com.example.pascm033.marvelcharacters;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterInfo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private ThumbnailInfo thumbnail;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ThumbnailInfo getThumbnail() {
        return thumbnail;
    }
}
