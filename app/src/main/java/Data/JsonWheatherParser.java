package Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Place;
import Model.Wheather;
import Model.Wind;
import Util.Utill;

/**
 * Created by SK on 1/5/2018.
 */
public class JsonWheatherParser {

    public static Wheather getWheather (String data) {
        Wheather wheather = new Wheather();

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();
            //.........Gettind data of co-ordinate
            JSONObject coordObj = Utill.getObject("coord", jsonObject);
            place.setLat(Utill.getFloat("lat", coordObj));
            place.setLon(Utill.getFloat("lon", coordObj));


            //.........Gettind data of sys
            JSONObject sysObj = Utill.getObject("sys", jsonObject);
            place.setCity(Utill.getString("country", sysObj));
            place.setSunrise(Utill.getInt("sunrise", sysObj));
            place.setSunset(Utill.getInt("sunset", sysObj));
            place.setCity(Utill.getString("name", jsonObject));
            place.setLastUpdate(Utill.getInt("dt", jsonObject));
            place.setCountry(Utill.getString("country", sysObj));
            wheather.place = place;

            //.........Gettind data of weather
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonweather = jsonArray.getJSONObject(0);
            wheather.currentCondition.setWheatherId(Utill.getInt("id", jsonweather));
            wheather.currentCondition.setDescription(Utill.getString("description", jsonweather));
            wheather.currentCondition.setCondition(Utill.getString("main", jsonweather));
            wheather.currentCondition.setIcon(Utill.getString("icon", jsonweather));

            //........ Getting data of Wind

            JSONObject windObject = Utill.getObject("wind", jsonObject);
            wheather.wind.setSpeed(Utill.getFloat("speed", windObject));
            wheather.wind.setDeg(Utill.getFloat("deg", windObject));

            //.........Getting data of Clouds

            JSONObject cloudObj = Utill.getObject("clouds", jsonObject);
            wheather.clouds.setPrecipitation(Utill.getInt("all", cloudObj));

            //.........Getting data of Main

            JSONObject mainObj = Utill.getObject("main", jsonObject);
            wheather.currentCondition.setTempreture(Utill.getFloat("temp", mainObj));
            wheather.currentCondition.setHumidity(Utill.getFloat("humidity", mainObj));
            wheather.currentCondition.setPressure(Utill.getFloat("pressure", mainObj));

            Log.v("DataSet", "Data Successfully Set");


            return wheather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return null;

    }
}
