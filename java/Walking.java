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

import groovy.json.JsonSlurper;

public class Walking {

    private static String GOOGLEMAPS = "http://maps.googleapis.com/maps/api/directions/json?";

	public static String walkTo(double lat, double lng, HashMap stop) throws IOException {
        String ORIGIN = "origin=" + lat + "," + lng;
        String DEST   = "&destination=" + ((HashMap)stop.get("location")).get("lat")
                                  + "," + ((HashMap)stop.get("location")).get("lng)");
        String INFO   = "&sensor=true&mode=walking";

        URL url = new URL(GOOGLEMAPS + ORIGIN + DEST + INFO);
        System.out.println(url);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

    public static String walkFrom(HashMap stop, double lat, double lng) throws IOException {
        String ORIGIN = "&destination=" + ((HashMap)stop.get("location")).get("lat")
                                  + "," + ((HashMap)stop.get("location")).get("lng)");
        String DEST   = "origin=" + lat + "," + lng;
        String INFO   = "&sensor=true&mode=walking";

        URL url = new URL(GOOGLEMAPS + ORIGIN + DEST + INFO);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

	public static void main(String[] args) {
		return;		
	}
}