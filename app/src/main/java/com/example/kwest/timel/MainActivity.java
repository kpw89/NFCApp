package com.example.kwest.timel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwest.timel.Model.Employee;
import com.example.kwest.timel.Model.MockJson;
import com.example.kwest.timel.Model.MockModel;
import com.example.kwest.timel.Model.TimeLog;
import com.example.kwest.timel.Tools.TempStore;
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
import retrofit2.http.Url;

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
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();

        JsonAPI jsonAPI = retrofit.create(JsonAPI.class);               //pass json api

//CALL TO SERVER
        String str_url = "LogWork/employee/"+TempStore.getTempStore().getId()+"/"
                +TempStore.getTempStore().getPwd();


        Call<Employee> call = jsonAPI.getData(str_url);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                if(!response.isSuccessful()){
                    if(response.code()==403)
                    Toast.makeText(getApplicationContext(),"Employee not authorized" ,Toast.LENGTH_SHORT ).show();
                    if(response.code()==401)
                        Toast.makeText(getApplicationContext(),"Incorrect password" ,Toast.LENGTH_SHORT ).show();
                    return;
                }
                String allTimes="";
                Employee map_data_from_server = response.body();
                Employee em1 = map_data_from_server;
                for(int i = 0; i<em1.getTimeLogString().size();i++){
                    allTimes+=em1.getTimeLogString().get(i).getT_start()+"\n";
                    allTimes+=em1.getTimeLogString().get(i).getT_stop()+"\n\n";
                }
                tv_test_data.setText(allTimes);

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
