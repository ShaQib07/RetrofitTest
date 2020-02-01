package com.shakib.retrofittest.doodle;

import retrofit2.Call;
import retrofit2.http.GET;

public interface doodleApi {

    String BASE_URL = "https://www.stg.liker.com/";

    @GET("get_categories")
    Call<CategoryResponse> getCategoryList();
}
