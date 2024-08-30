package com.example.irrigation_system;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private RecyclerView view;
    private RecyclerView.Adapter adapterHourly ;
    TextView weathertemp , humidityvalue ,windspeedvalue , rainvalue , weather_status ;
    EditText cityEditText ;
    Button searchWeather ;
    ImageView imageView1 ;
    String url ;

    class GetWeather extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject mainObject = jsonObject.getJSONObject("main");
                JSONObject windObject = jsonObject.getJSONObject("wind");
                JSONArray weatherArray = jsonObject.getJSONArray("weather");
                if (weatherArray.length() > 0) {
                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    String description = weatherObject.getString("description");
                    String iconCode = weatherObject.getString("icon");
                    int resourceId = getResources().getIdentifier(iconCode, "drawable", getPackageName());


                    imageView1.setImageResource(resourceId);
                    weather_status.setText(description);
                }
                double temperatureKelvin = mainObject.getDouble("temp");
                double humidity = mainObject.getDouble("humidity");
                double speed = windObject.getDouble("speed");



                // Conversion en Celsius
                double temperatureCelsius = (temperatureKelvin - 273.15);

                // Convertir en int (arrondi)
                int temperatureCelsiusInt = (int) Math.round(temperatureCelsius);


                // Affichage des valeurs
                weathertemp.setText(temperatureCelsiusInt + "°C");
                humidityvalue.setText(humidity + "%");
                windspeedvalue.setText(speed + "%");



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button next7days = findViewById(R.id.next7days) ;
        Button tosystem = findViewById(R.id.tosystem) ;
        weathertemp = findViewById(R.id.weathertemp);
        humidityvalue = findViewById(R.id.humidityvalue);
        windspeedvalue = findViewById(R.id.windspeedvalue) ;
        rainvalue = findViewById(R.id.rainvalue);
        cityEditText = findViewById(R.id.cityEditText);
        searchWeather = findViewById(R.id.searchWeather);
        weather_status = findViewById(R.id.weather_status) ;
        imageView1 = findViewById(R.id.imageView1) ;

        searchWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityEditText.getText().toString();
                if (city != null && !city.isEmpty()) {
                    url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=bcfc12d05fefbe22affde6f78e20e82d";
                    GetWeather task = new GetWeather();
                    task.execute(url);
                } else {
                    Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initRecyclerView();



        tosystem.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(MainActivity.this, com.example.irrigation_system.Home_page.class);
                                            startActivity(intent);
                                        }

                                    }
        );
        next7days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FutureActivity.class);
                startActivity(intent);
            }
        });




    }


    private void initRecyclerView() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("9 pm", 28, "cloudy"));
        items.add(new Hourly("10 pm", 29, "sunny"));
        items.add(new Hourly("11 pm", 30, "wind"));
        items.add(new Hourly("12 pm", 31, "rainy"));
        items.add(new Hourly("1 am", 32, "storm"));
        view = findViewById(R.id.view);
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterHourly = new HourlyAdapter(items);
        view.setAdapter(adapterHourly);

    }
}

