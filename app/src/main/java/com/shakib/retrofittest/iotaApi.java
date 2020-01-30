package com.shakib.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface iotaApi {

    String BASE_URL = "https://iotait.tech/";

    @GET("event_task")
    Call<iotaTest> getGroups();
}
