package com.lsttsl.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AsynctaskActivity extends AppCompatActivity {

    TextView tv;
    Button startBtn;
    Button stopBtn;
    AsyncTask<Integer, Integer, Integer> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        tv = findViewById(R.id.as_tv);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);
        tv.setText("현재 받아온 온도가 없습니다.");

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });

    }

    int rnd;

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {


        @Override
        protected Integer doInBackground(Integer... integers) {

            while (!isCancelled()) {
                rnd = (new Random()).nextInt(15) + 10;
                publishProgress(rnd);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return rnd;
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onProgressUpdate(Integer... values) {
            int rnds = values[0];
            tv.setText(String.format("현재 온도는 %d도 입니다.", rnds));
        }

        @Override
        protected void onPostExecute(Integer integer) {
            tv.setText("완료되었습니다");
        }

        @Override
        protected void onCancelled() {
            tv.setText("취소 되었습니다.");
        }
    }

}

















