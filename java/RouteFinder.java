// Class for determining routes and stops for shuttle service

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RouteFinder {

    public static float distance(float x1, float y1, float x2, float y2) {
        float dist = Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1-y2,2) );
        return dist;
    }

    public static String getNearestStop(float lat, float lng) {

        List routes;
        List stops;
        int min_dist = -1;
        String min_stop;

        return min_stop;
    }

    public static String getNearestStopOnRoute(String route,
                                               float lat, float lng) {
        List stops;
        int min_dist = -1;
        String min_stop;

        return min_stop;
    }

    public static void main(String[] args) {
        
    }

}