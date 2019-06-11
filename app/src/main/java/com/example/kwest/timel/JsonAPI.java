package com.example.kwest.timel;

import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonAPI {

    //alt rest/jsonTest
    @GET("LogWork/jsonTest")
    Call<List<MockJson>> getData();

}
