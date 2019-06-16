package com.example.kwest.timel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kwest.timel.Tools.TempStore;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


public void getEmployee1 (View view){
    TempStore.getTempStore().setId("0");
    startActivity(new Intent(this,Em1.class));
}

    public void getEmployee2 (View view){
        TempStore.getTempStore().setId("1");
        startActivity(new Intent(this,Em2.class));
    }

    public void getEmployee3 (View view){
        TempStore.getTempStore().setId("2");
        startActivity(new Intent(this,Em3.class));
    }

}
