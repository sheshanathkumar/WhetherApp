package Data;

import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utill;

/**
 * Created by SK on 1/5/2018.
 */
public class WheatherHttpClient {

    public String getWheatherData (String place) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(Utill.BASE_URL + place)).openConnection() ;
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            Log.v("connectionHTTP", "Conection OK");

            //Read Data from Connec11tion.......

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            Log.v("DataFetch", "Fetching Done");

            while ( (line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");

            }
            inputStream.close();
            connection.disconnect();

            //Log.v("DataFetch", "Fetching Done");

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
