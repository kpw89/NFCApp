package com.example.kwest.timel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwest.timel.Model.Employee;
import com.example.kwest.timel.Model.TimeLog;
import com.example.kwest.timel.Tools.TempStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Admin extends AppCompatActivity {

    private TextView tv_test_data;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tv_test_data = findViewById(R.id.tv_show_model_admin);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(15,TimeUnit.SECONDS );
        client.readTimeout(15,TimeUnit.SECONDS );
        client.writeTimeout(15,TimeUnit.SECONDS );

        retrofit = new Retrofit.Builder()
                //"https://jsonplaceholder.typicode.com/"
                //29
                .baseUrl("http://192.168.188.21:8080/")         //base url
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        AdminAPI jsonAPI = retrofit.create(AdminAPI.class);

        String str_url = "LogWork/employee/"+TempStore.getTempStore().getId()+"/"
                +TempStore.getTempStore().getPwd();

        Call<String> call = jsonAPI.getData(str_url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(!response.isSuccessful()){
                    if(response.code()==403)
                        Toast.makeText(getApplicationContext(),"Employee not authorized" ,Toast.LENGTH_SHORT ).show();
                    if(response.code()==401)
                        Toast.makeText(getApplicationContext(),"Incorrect password" ,Toast.LENGTH_SHORT ).show();
                    return;
                }
                String allTimes="";
                allTimes = response.body();
                tv_test_data.setText(allTimes);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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
