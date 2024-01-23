package com.example.weathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Gson gson;
    private OkHttpClient client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new OkHttpClient();
                GetDummyAsyncTask task = new GetDummyAsyncTask();
                task.execute();
            }
        });


    }
    private class GetDummyAsyncTask extends AsyncTask<String, Void, WeatherData> {
        protected WeatherData doInBackground(String... urls) {
            WeatherData obj = null;
            String search = ((TextInputEditText)findViewById(R.id.inputText)).getText().toString();
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + search + "&appid="; // API-Key einfügen
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                    .get()
                    .build();
            Log.d("JSONDEMO", "REQUEST:" + request);
            String responseStr = "";
            try  {
                Response response = client.newCall(request).execute();
                responseStr =  response.body().string();
                Log.d("JSONDEMO", "SERVER ANSWER:" + responseStr);
                obj = new Gson().fromJson(responseStr, WeatherData.class);
                Log.d("JSONDEMO", "AS OBJECT:" + obj);

            } catch (Exception e)
            {
                Log.e("JSONDEMO", String.valueOf(e));
            }

            return obj;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(WeatherData result) {
            ((TextView)findViewById(R.id.desc)).setText(result.getWeather().get(0).getDescription());
            ((TextView)findViewById(R.id.temp)).setText(String.format( "%.2f°C", result.getMain().getTempCel()));
            ((TextView)findViewById(R.id.hum)).setText(result.getMain().getHumidity() + "%");
            ((TextView)findViewById(R.id.current)).setText(result.getName());
        }
    }
}