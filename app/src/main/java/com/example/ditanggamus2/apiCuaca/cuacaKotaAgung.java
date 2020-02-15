package com.example.ditanggamus2.apiCuaca;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class cuacaKotaAgung {

    Context context;
    String kota="Kotaagung, ID";
    String API = "d554c7672ac3190dfd96a2211d3935ea";

    String suhu,suhuMin,suhuMax;

    class weatherTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + kota + "&units=metric&appid=" + API);
            return response;
        }
    }

    protected void onPostExecute(String result){

        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONObject main = jsonObj.getJSONObject("main");
            JSONObject sys = jsonObj.getJSONObject("sys");
            JSONObject wind = jsonObj.getJSONObject("wind");
            JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

            Long updatedAt = jsonObj.getLong("dt");
            String updatedAtText = "Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
            String temp = main.getString("temp") + "°C";
            String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
            String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
            String pressure = main.getString("pressure");
            String humidity = main.getString("humidity");

            Long sunrise = sys.getLong("sunrise");
            Long sunset = sys.getLong("sunset");
            String windSpeed = wind.getString("speed");
            String weatherDescription = weather.getString("description");

            String address = jsonObj.getString("name") + ", " + sys.getString("country");

            suhu = temp;
            suhuMin = tempMin;
            suhuMax = tempMax;

        }catch (JSONException e){
            Toast.makeText(context,"Ini wisata "+ e,Toast.LENGTH_SHORT).show();
        }

    }


    public String getSuhu() {
        return suhu;
    }

    public String getSuhuMin() {
        return suhuMin;
    }

    public String getSuhuMax() {
        return suhuMax;
    }
}
