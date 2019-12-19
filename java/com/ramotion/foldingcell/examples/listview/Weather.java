package com.ramotion.foldingcell.examples.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.examples.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather extends AppCompatActivity{
    public static final int LOAD_SUCCESS = 101;

    private LocationManager lm;
    private Location location;

    private static TextView temp;
    private static TextView feels;
    private static TextView loc;
    private static ImageView weather_icon;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        temp = (TextView) findViewById(R.id.temp);
        feels = (TextView) findViewById(R.id.feels_humidity);
        loc = (TextView) findViewById(R.id.location);
        weather_icon = (ImageView) findViewById(R.id.imageView);

        getJSON();
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<Weather> weakReference;

        public MyHandler(Weather weather) {
            weakReference = new WeakReference<Weather>(weather);
        }

        @Override
        public void handleMessage(Message msg) {
            Weather weather = weakReference.get();

            if (weather != null) {
                switch (msg.what) {
                    case LOAD_SUCCESS:
                        String jsonString = (String)msg.obj;
                        Log.i("test", jsonString);
                        Parser(jsonString);
                        break;
                }
            }
        }
    }

    public void  getJSON() {
        Thread thread = new Thread(new Runnable() {

            public void run() {

                String result;
                String key = "78583c6eb9ba0b173a79aeefb4a68a6c";

                float longitude = (float)location.getLongitude();
                float latitude = (float)location.getLatitude();

                Log.i("lat", Float.toString(latitude));
                Log.i("lon", Float.toString(longitude));

                try {
                    URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&APPID=" + key);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();

                    int responseStatusCode = httpURLConnection.getResponseCode();

                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();

                    }

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result = sb.toString().trim();

                } catch (Exception e) {
                    result = e.toString();
                }

                Message message = mHandler.obtainMessage(LOAD_SUCCESS, result);
                mHandler.sendMessage(message);
            }

        });
        thread.start();
    }

    public static String[] Parser(String jsonString) {

        String weather = null;
        float cur_temp = 0;
        float feels_like = 0;
        float temp_min = 0;
        float temp_max = 0;
        int humidity = 0;
        String location = null;

        String[] arraysum = new String[7];
        try {
            JSONObject jObject = new JSONObject(jsonString);

            weather = jObject.getJSONArray("weather").getJSONObject(0).getString("main");
            cur_temp = jObject.getJSONObject("main").getLong("temp") - 273.15F;
            feels_like = jObject.getJSONObject("main").getLong("feels_like") - 273.15F;
            temp_min = jObject.getJSONObject("main").getLong("temp_min") - 273.15F;
            temp_max = jObject.getJSONObject("main").getLong("temp_max") - 273.15F;
            humidity = jObject.getJSONObject("main").getInt("humidity");
            location = jObject.getString("name");

            arraysum[0] = weather;
            arraysum[1] = Float.toString(cur_temp);
            arraysum[2] = Float.toString(feels_like);
            arraysum[3] = Float.toString(temp_min);
            arraysum[4] = Float.toString(temp_max);
            arraysum[5] = Integer.toString(humidity);
            arraysum[6] = location;

            Log.i("Test", arraysum[0]);

            loc.setText(location);
            temp.setText(String.format("%.1f", cur_temp) + "℃");
            feels.setText("체감온도 " + String.format("%.1f", feels_like) + "℃\n습도 " + humidity);

            if (arraysum[0].equals("Clear"))
                weather_icon.setImageResource(R.drawable.sunny);
            if (arraysum[0].equals("Cloudy"))
                weather_icon.setImageResource(R.drawable.partly_cloud);
            if (arraysum[0].equals("Clouds"))
                weather_icon.setImageResource(R.drawable.cloudy);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;
    }
}