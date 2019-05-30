package com.example.kwest.timel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwest.timel.Model.MockModel;

import java.io.IOException;
import java.util.List;

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
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")         //base url
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonAPI jsonAPI = retrofit.create(JsonAPI.class);               //pass json api

//CALL TO SERVER
        Call<List<MockModel>> call = jsonAPI.getData();
        call.enqueue(new Callback<List<MockModel>>() {
            @Override
            public void onResponse(Call<List<MockModel>> call, Response<List<MockModel>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code "+response.code() ,Toast.LENGTH_SHORT ).show();
                    return;
                }

                List<MockModel> data_from_server = response.body();
                for(MockModel mockModel : data_from_server){
                    String content ="";
                    content+="userid "+ mockModel.getUserId() +"\n";
                    content+="id "+ mockModel.getId() +"\n";
                    content+="title "+ mockModel.getTitle() +"\n";
                    content+="body "+mockModel.getBody()+"\n\n";
                    tv_test_data.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<MockModel>> call, Throwable t) {
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
