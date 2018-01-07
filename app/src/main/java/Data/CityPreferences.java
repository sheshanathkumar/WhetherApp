package Data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by SK on 1/6/2018.
 */
public class CityPreferences {

    SharedPreferences pref;

    public CityPreferences(Activity activity) {
        pref = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity () {
        return pref.getString("city", "Patna, BH");
    }

    public void setCity (String city){
        pref.edit().putString("city", city).commit();
    }

}
