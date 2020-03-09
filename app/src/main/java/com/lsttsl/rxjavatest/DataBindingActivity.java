package com.lsttsl.rxjavatest;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;


import com.lsttsl.rxjavatest.databinding.ActivityDataBindingBinding;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;

public class DataBindingActivity extends Activity {

    private final TemperatureManager manager = new TemperatureManager();
    private ActivityDataBindingBinding binding;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.currentTemperatureView.setText("받아온 온도가 없습니다.");

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            // Create fake data.
            int nextTemperature = (new Random()).nextInt(15) + 10;
            manager.setTemperature( new TemperatureManager.Temperature(nextTemperature));
        }, 0L, 3, TimeUnit.SECONDS);

        disposable = manager.updateEvent().subscribe(this::updateView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @SuppressLint("DefaultLocale")
    private void updateView(TemperatureManager.Temperature  temperature){
        binding.currentTemperatureView.setText(String.format("현재 온도는 %d도 입니다.", temperature.getDegree()));
    }
}
