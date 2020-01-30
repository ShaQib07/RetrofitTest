package com.shakib.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface localhostAPi {

    String BASE_URL = "http://10.0.2.2/Projects/Dota2Heroes/";

    @GET("strength")
    Call<List<Dota2Hero>> getDota2Heroes();
}
