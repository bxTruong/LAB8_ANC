package com.example.lab8_anc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBai1(View view) {
        Intent intent=new Intent(this,OpenBai1.class);
        startActivity(intent);
    }

    public void openBai2(View view) {
        Intent intent=new Intent(this,OpenBai2.class);
        startActivity(intent);
    }

    public void openBai3(View view) {
        Intent intent=new Intent(this,OpenBai3.class);
        startActivity(intent);
    }
}
