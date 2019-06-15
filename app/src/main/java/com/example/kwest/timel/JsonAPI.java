package com.example.kwest.timel;

import com.example.kwest.timel.Model.Employee;
import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonAPI {

    //<HashMap<String, Employee>>
    //("LogWork/all")

    @GET
    Call<Employee> getData(@Url String url);

}
