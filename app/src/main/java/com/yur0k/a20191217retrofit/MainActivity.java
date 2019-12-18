package com.yur0k.a20191217retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://dataservice.accuweather.com/";
    public static final String APPI_KEY = "dNCJ5Bk0LjZE75BNfUFBgAdOFj6I7bjI";
    private static final String TAG = "Log" ;
    public AccuWeather weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewTemp = findViewById(R.id.textViewTemp);

        Log.e(TAG,"создаем активити");

        try {
            Log.i(TAG, "сейчас будем вызывать getWeather");

            WeatherModel model = getWeather(294021);

            Log.i(TAG,"model.getLink" + model.getLink());

            if (model == null) {
                Log.i(TAG, "model = null");
                return;
            }
            Log.i(TAG, "model != null получаем значение");

            textViewTemp.setText(Double.toString(model.getTemperature().getMetric().getValue()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private WeatherModel getWeather (Integer cityName) throws Exception {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Log.i(TAG, "retrofit ");

        weatherApi = retrofit.create(AccuWeather.class);

        Log.i(TAG, "weatherApi " + weatherApi.toString());

        Call<WeatherModel> call = weatherApi.getWeather(cityName,APPI_KEY);

        Log.i(TAG, "вызов Call " + call.request());

        Response<WeatherModel> response = call.execute();

        Log.i(TAG,"call.execute() = " + call.execute().toString());
        Log.i(TAG,"response " + response);

        if (response.isSuccessful()) return response.body();
        else
            throw new Exception(response.errorBody().string(),null);
    }

}
