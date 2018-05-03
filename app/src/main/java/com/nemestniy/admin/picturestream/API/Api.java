package com.nemestniy.admin.picturestream.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/photos/random")
    Call<ArrayList<ExampleResponse>> getPicture(@Query("client_id") String key, @Query("count") int count);
}
