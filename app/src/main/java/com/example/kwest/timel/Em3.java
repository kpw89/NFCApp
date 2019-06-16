package com.example.kwest.timel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.kwest.timel.Tools.TempStore;

public class Em3 extends AppCompatActivity {
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em3);
        editText = findViewById(R.id.edit_pwd_em3);
    }

    public void getPwd(View view){
        TempStore.getTempStore().setPwd(editText.getText().toString());
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
