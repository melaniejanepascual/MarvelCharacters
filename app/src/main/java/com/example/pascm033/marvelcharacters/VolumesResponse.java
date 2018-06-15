package com.example.pascm033.marvelcharacters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumesResponse {

    @SerializedName("items")
    private List<VolumeData> volumeInfoList;

    public List<VolumeData> getVolumeInfoList() {
        return volumeInfoList;
    }
}
