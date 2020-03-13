package com.lsttsl.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class HandlerActivity extends AppCompatActivity {

    TextView tv;
    Button startBtn;
    Button stopBtn;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        tv =findViewById(R.id.handler_text);
        tv.setText("현재 받아온 온도가 없습니다.");
        startBtn=findViewById(R.id.start_btn);
        stopBtn= findViewById(R.id.stop_btn);
        handler =new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                CreateThread();
            }
        };

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                tv.setText("온도계가 정지 되었습니다.");
            }
        });

    }



    @SuppressLint("DefaultLocale")
    void CreateThread(){
        int rnd = (new Random()).nextInt(15) + 10;
        tv.setText(String.format("현재 온도는 %d도 입니다.", rnd));
        handler.postDelayed(runnable, 3000);
    }

}
