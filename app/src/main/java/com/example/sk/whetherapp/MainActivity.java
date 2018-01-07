package com.example.sk.whetherapp;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import Data.CityPreferences;
import Data.JsonWheatherParser;
import Data.WheatherHttpClient;
import Model.Wheather;

public class MainActivity extends AppCompatActivity {

    private TextView city, wind, humidity, pressure;
    private TextView condition, sunrise, sunset, update, temp;
    private ImageView iconView;
    Wheather wheather = new Wheather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (TextView) findViewById(R.id.cityText);
        wind = (TextView) findViewById(R.id.windText);
        humidity = (TextView) findViewById(R.id.humidityText);
        pressure = (TextView) findViewById(R.id.pressureText);
        condition = (TextView) findViewById(R.id.conditionText);
        sunrise = (TextView) findViewById(R.id.sunriseText);
        sunset = (TextView) findViewById(R.id.sunsetText);
        update = (TextView) findViewById(R.id.lastUpdateText);
        temp = (TextView) findViewById(R.id.tempratureText);
        iconView = (ImageView) findViewById(R.id.imageView);


        //CityPreferences cityPreferences = new CityPreferences(MainActivity.this);

        //renderWeatherData(cityPreferences.getCity());
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city +"&appid=44775afd0ae7e8619045a07683abf631"});
    }


    private class WeatherTask extends AsyncTask<String, Void, Wheather> {
        @Override
        protected Wheather doInBackground(String... strings) {

            String data = ((new WheatherHttpClient()).getWheatherData(strings[0]));
            wheather = JsonWheatherParser.getWheather(data);

            Log.v("Kaiseho ", wheather.place.getCity());
            return wheather;
        }

        @Override
        protected void onPostExecute(Wheather wheather) {
            super.onPostExecute(wheather);

            DateFormat df = DateFormat.getTimeInstance();
            String sunRise = df.format(wheather.place.getSunrise());
            String sunSet = df.format(wheather.place.getSunset());
            String upDate = df.format(wheather.place.getLastUpdate());

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempp = decimalFormat.format(wheather.currentCondition.getTempreture());


            city.setText(wheather.place.getCity() + " " + wheather.place.getCountry());
            wind.setText("Wind : " + wheather.wind.getSpeed() + " m/s" );
            humidity.setText("Humidity : "+ wheather.currentCondition.getHumidity()+ "%");
            pressure.setText("Pressure :" + wheather.currentCondition.getPressure()+"hPa");
            temp.setText(tempp + "C");
            condition.setText("Condition : " + wheather.currentCondition.getCondition());
            sunrise.setText("Sunrise : "+ sunRise);
            sunset.setText("Sunset : "+ sunSet );
            update.setText("Last Update "+ upDate);

        }
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change City..");

        final EditText cityText = new EditText(MainActivity.this);
        cityText.setInputType(InputType.TYPE_CLASS_TEXT);
        cityText.setHint("Delhi, IN");
        builder.setView(cityText);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CityPreferences cityPreferences = new CityPreferences(MainActivity.this);
                cityPreferences.setCity(cityText.getText().toString());
                String newCity = cityPreferences.getCity();
                renderWeatherData(newCity);
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.change_city)
            showInputDialog();

        return super.onOptionsItemSelected(item);

    }
}
