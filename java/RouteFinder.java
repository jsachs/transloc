// Class for determining the appropriate route and stop

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


public class RouteFinder {

	public static double latLngDist(double x1, double y1, double x2, double y2) {
		double dist = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		return dist;
	}

	public static HashMap getNearestStop(double lat, double lng) {
        ArrayList<HashMap> routes = TransLocAPI.getRoutes("100");
        ArrayList<HashMap> stops  = TransLocAPI.getStops("100");
        int min_dist = -1;
        HashMap min_stop;
        ArrayList<int> route_ids;
        for (HashMap route: routes)
            route_ids.append(route.get("route_id"));

        // Make sure North, South, East, Central are running

        for (HashMap stop : stops) {
            ArrayList<int> route_list = stop.get("routes");
            for (int id : route_list) {
                if (route_ids.contains(id)) {
                    double stop_lat = stop.get("location").get("lat");
                    double stop_lng = stop.get("location").get("lng");
                    double dist = latLngDist(stop_lat, stop_lng, lat, lng);
                    if (min_dist < 0 || dist < min_dist) {
                        min_dist = dist;
                        min_stop = stop;
                    }
                }
            }
        }
        return min_stop;

    }

	public static HashMap getNearestStopOnRoute(HashMap route, double lat, double lng) {
        ArrayList<HashMap> stops  = TransLocAPI.getStops("100");
        int min_dist = -1;
        HashMap min_stop;

        // Make sure North, South, East, Central are running

        for (HashMap stop : stops) {
            if stop.get("routes").contains(route.get("route_id")) {
                    double stop_lat = stop.get("location").get("lat");
                    double stop_lng = stop.get("location").get("lng");
                    double dist = latLngDist(stop_lat, stop_lng, lat, lng);
                    if (min_dist < 0 || dist < min_dist) {
                        min_dist = dist;
                        min_stop = stop;
                    }
                }
            }
        }
        return min_stop;
    }

	public static String[] getDirections(double lat_i, double lng_i,
                                             double lat_f, double lng_f);

	public static void main(String[] args) {
		return;
	}
}