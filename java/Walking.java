// Class for getting Google Maps walking directions

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.Math;

import org.json.*;


public class Walking {

    private static String GOOGLEMAPS = "http://maps.googleapis.com/maps/api/directions/json?";

	public static ArrayList<String> walkTo(double lat, double lng, HashMap stop) throws IOException, JSONException {
        String ORIGIN = "origin=" + lat + "," + lng;
        String DEST   = "&destination=" + ((JSONObject)stop.get("location")).getString("lat")
                                  + "," + ((JSONObject)stop.get("location")).getString("lng");
        String INFO   = "&sensor=true&mode=walking";

        URL url = new URL(GOOGLEMAPS + ORIGIN + DEST + INFO);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        ArrayList<String> directions = new ArrayList<String>();
        JSONObject jObj = new JSONObject(builder.toString());
        JSONArray steps = jObj.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");

        for (int i=0; i < steps.length(); i++)
            directions.add(steps.getJSONObject(i).getString("html_instructions"));

        return directions;
    }

    public static ArrayList<String> walkFrom(HashMap stop, double lat, double lng) throws IOException, JSONException {
        String DEST   = "&destination=" + ((JSONObject)stop.get("location")).getString("lat")
                                  + "," + ((JSONObject)stop.get("location")).getString("lng");
        String ORIGIN = "origin=" + lat + "," + lng;
        String INFO   = "&sensor=true&mode=walking";

        URL url = new URL(GOOGLEMAPS + ORIGIN + DEST + INFO);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        ArrayList<String> directions = new ArrayList<String>();
        JSONObject jObj = new JSONObject(builder.toString());
        JSONArray steps = jObj.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");

        for (int i=0; i < steps.length(); i++)
            directions.add(steps.getJSONObject(i).getString("html_instructions"));
        
        return directions;
    }

	public static void main(String[] args) throws IOException, JSONException {
        ArrayList<String> to = walkTo(41.791393,-87.599776,TransLocAPI.getStops("100").get(0));
        System.out.println(to);
        ArrayList<String> from = walkFrom(TransLocAPI.getStops("100").get(0),41.791393,-87.599776);
        System.out.println(from);
		return;		
	}
}






