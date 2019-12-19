package com.ramotion.foldingcell.examples.listview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ramotion.foldingcell.examples.R;

public class Pedometer extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    TextView tvStepCount;
    TextView tvStepCount2;
    TextView tvStepCount3;
    ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        tvStepCount = (TextView)findViewById(R.id.tvStepCount);
        tvStepCount2=(TextView)findViewById(R.id.tvStepCount2);
        tvStepCount3=(TextView)findViewById(R.id.tvStepCount3);
        int progressValue;
        pBar= (ProgressBar)findViewById(R.id.progressBar);
        pBar.setMax(300);
        pBar.setProgress(30);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            pBar.setProgress((int)event.values[0]);
            tvStepCount.setText("남은 걸음 수 : " + Float.toString(10000-event.values[0]));
            tvStepCount2.setText("이동 걸음 수 : " + Float.toString(event.values[0]));
            tvStepCount3.setText("이동거리 : "+ Float.toString((float)((double)event.values[0]*0.7))+"m");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}