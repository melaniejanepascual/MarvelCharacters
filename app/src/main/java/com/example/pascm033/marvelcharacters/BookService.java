package com.example.pascm033.marvelcharacters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("/books/v1/volumes")
    Call<VolumesResponse> getBooks(@Query("q") String query, @Query("maxResults") int maxResults, @Query("printType") String printType);

}
