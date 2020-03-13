package com.lsttsl.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ThreadActivity extends AppCompatActivity {

    Button startBtn;
    Button stopBtn;
    TextView tv;
    ThreadLST threadLST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tv = findViewById(R.id.threadText);
        tv.setText("현재 받아온 온도가 없습니다.");

        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);

        threadLST = new ThreadLST();


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadLST.start();
                ischeck = true;
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ischeck = false;
            }
        });


    }

    boolean ischeck = false;

    class ThreadLST extends Thread {

        @SuppressLint("DefaultLocale")
        @Override
        public void run() {

            while (ischeck) {
                int rnd = (new Random()).nextInt(15) + 10;
                runOnUiThread(() ->
                        tv.setText(String.format("현재 온도는 %d도 입니다.", rnd))
                );


                try {
                    ThreadLST.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadLST.interrupt();
    }
}







