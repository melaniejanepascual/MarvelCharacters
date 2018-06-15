package com.example.pascm033.marvelcharacters;

import com.google.gson.annotations.SerializedName;

public class VolumeData {

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
