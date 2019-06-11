package com.example.kwest.timel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        retrofit = new Retrofit.Builder()
                //"https://jsonplaceholder.typicode.com/"

                .baseUrl("http://192.168.188.21:8080/")         //base url
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        JsonAPI jsonAPI = retrofit.create(JsonAPI.class);               //pass json api

//CALL TO SERVER
        Call<List<MockJson>> call = jsonAPI.getData();
        call.enqueue(new Callback<List<MockJson>>() {
            @Override                                                       //MockModel
            public void onResponse(Call<List<MockJson>> call, Response<List<MockJson>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code "+response.code() ,Toast.LENGTH_SHORT ).show();
                    return;
                }
/*
                List<MockModel> data_from_server = response.body();
                for(MockModel mockModel : data_from_server){
                    String content ="";
                    content+="userid "+ mockModel.getUserId() +"\n";
                    content+="id "+ mockModel.getId() +"\n";
                    content+="title "+ mockModel.getTitle() +"\n";
                    content+="body "+mockModel.getBody()+"\n\n";
                    tv_test_data.append(content);
                }
                */

            }

            @Override
            public void onFailure(Call<List<MockJson>> call, Throwable t) {
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
