package com.lsttsl.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button dataBtn = findViewById(R.id.btn_databinding);
        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , DataBindingActivity.class));
            }
        });

        Button threadBtn = findViewById(R.id.th);
        threadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThreadActivity.class));
            }
        });

        Button handlerBtn = findViewById(R.id.handler_btn);
        handlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HandlerActivity.class));
            }
        });

        Button atBtn = findViewById(R.id.asynctask_btn);
        atBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AsynctaskActivity.class));
            }
        });


    }
}
