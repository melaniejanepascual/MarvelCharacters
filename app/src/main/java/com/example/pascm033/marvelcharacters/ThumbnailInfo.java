package com.example.pascm033.marvelcharacters;

import com.google.gson.annotations.SerializedName;

public class ThumbnailInfo {

    @SerializedName("path")
    private String path;

    @SerializedName("extension")
    private String extension;

    public String getPath() {
        return path;
    }
    public String getExtension() {
        return extension;
    }
}
