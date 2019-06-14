package com.example.kwest.timel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwest.timel.Model.Employee;
import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;
import com.example.kwest.timel.Model.TimeLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv_test_data;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test_data = findViewById(R.id.tv_show_model);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(15,TimeUnit.SECONDS );
        client.readTimeout(15,TimeUnit.SECONDS );
        client.writeTimeout(15,TimeUnit.SECONDS );

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TimeLog.class,new DeserializeTimeLog() )
                .create();

        retrofit = new Retrofit.Builder()
                //"https://jsonplaceholder.typicode.com/"

                .baseUrl("http://192.168.188.21:8080/")         //base url
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        JsonAPI jsonAPI = retrofit.create(JsonAPI.class);               //pass json api

//CALL TO SERVER
        Call<Employee> call = jsonAPI.getData();
        call.enqueue(new Callback<Employee>() {
            @Override                                                       //MockModel
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code "+response.code() ,Toast.LENGTH_SHORT ).show();
                    return;
                }
                Employee map_data_from_server = response.body();
                Employee em1 = map_data_from_server;
                em1.getTimeLogString();
                tv_test_data.setText(em1.getTimeLogString().getT_start());
              /*  map_data_from_server.get("Emp 1").getTimeLog().setT_start(new Timestamp(System.currentTimeMillis()));
                map_data_from_server.get("Emp 1").getTimeLog().setT_stop(new Timestamp(System.currentTimeMillis()));
                String cnt =  map_data_from_server.get("Emp 1").getStr_Name()+"\n";
                 cnt += map_data_from_server.get("Emp 1").getTimeLog().getT_start().toString()+"\n";
                cnt += map_data_from_server.get("Emp 1").getTimeLog().getT_stop().toString()+"\n";
                tv_test_data.setText(cnt);*/
/*
                List<MockModel> map_data_from_server = response.body();
                for(MockModel mockModel : map_data_from_server){
                    String content ="";
                    content+="userid "+ mockModel.getUserId() +"\n";
                    content+="id "+ mockModel.getId() +"\n";
                    content+="title "+ mockModel.getTitle() +"\n";
                    content+="body "+mockModel.getBody()+"\n\n";
                    tv_test_data.append(content);
                }*/

           /*     MockJson map_data_from_server = response.body();
                String content="";
                content+= map_data_from_server.getName1();
                content+= map_data_from_server.getName2();

                    tv_test_data.setText(content); */

               // tv_test_data.setText("asjdfkadsfjladskjf");
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                if(t instanceof IOException){
                    Toast.makeText(getApplicationContext(), "network failure",Toast.LENGTH_LONG ).show();
                    tv_test_data.setText(t.getMessage());
                }

                else
                    Toast.makeText(getApplicationContext(),"conversion issue" ,Toast.LENGTH_LONG ).show();

            }
        });

    }
}
