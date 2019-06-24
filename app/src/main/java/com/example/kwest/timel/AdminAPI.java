package com.example.kwest.timel;

import com.example.kwest.timel.Model.Employee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface AdminAPI {



    @GET
    Call<String> getData(@Url String url);
}
