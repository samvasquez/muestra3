package com.sena.edu.co;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
    }

    public void BtnIngresar(View view) {
        Intent intent=new Intent (this,MainLogin.class);
        startActivity(intent);
    }
}