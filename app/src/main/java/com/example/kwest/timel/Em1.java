package com.example.kwest.timel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.kwest.timel.Tools.TempStore;

public class Em1 extends AppCompatActivity {

    EditText editText;
    EditText editText_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_em1);
        editText = findViewById(R.id.edit_pwd_em1);
        editText_user = findViewById(R.id.edit_user);
    }

    public void getPwd(View view){
        TempStore.getTempStore().setId(editText_user.getText().toString());
        TempStore.getTempStore().setPwd(editText.getText().toString());
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
