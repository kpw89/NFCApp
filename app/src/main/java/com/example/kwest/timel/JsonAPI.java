package com.example.kwest.timel;

import com.example.kwest.timel.Model.Employee;
import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonAPI {

    //Call<List<Post>>
    // Call<MockJson>
    @GET("LogWork/all")
    Call<Map<String,Employee>> getData();

}
