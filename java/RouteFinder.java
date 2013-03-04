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
import java.lang.Math;


public class RouteFinder {

    // TODO use getAgencies to pull uchicago id

	public static double latLngDist(double x1, double y1, double x2, double y2) {
		double dist = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		return dist;
	}

	public static HashMap getNearestStop(double lat, double lng) throws IOException {
        ArrayList<HashMap> routes = TransLocAPI.getRoutes("100");
        ArrayList<HashMap> stops  = TransLocAPI.getStops("100");
        double min_dist = -1;
        HashMap min_stop = new HashMap();
        ArrayList<Integer> route_ids = new ArrayList<Integer>();
        for (HashMap route: routes)
            route_ids.add((Integer)route.get("route_id"));

        // TODO make sure North, South, East, Central are running

        for (HashMap stop : stops) {
            ArrayList<Integer> route_list = (ArrayList<Integer>)stop.get("routes");
            for (Integer id : route_list) {
                if (route_ids.contains(id)) {
                    double stop_lat = Double.valueOf((String)((HashMap)stop.get("location")).get("lat")).doubleValue();
                    double stop_lng = Double.valueOf((String)((HashMap)stop.get("location")).get("lng")).doubleValue();
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

	public static HashMap getNearestStopOnRoute(HashMap route, double lat, double lng) throws IOException {
        ArrayList<HashMap> stops  = TransLocAPI.getStops("100");
        double min_dist = -1;
        HashMap min_stop = new HashMap();

        // TODO make sure North, South, East, Central are running

        for (HashMap stop : stops) {
            if (((ArrayList<Integer>)stop.get("routes")).contains((Integer)route.get("route_id"))) {
                    double stop_lat = Double.valueOf((String)((HashMap)stop.get("location")).get("lat")).doubleValue();
                    double stop_lng = Double.valueOf((String)((HashMap)stop.get("location")).get("lng")).doubleValue();
                    double dist = latLngDist(stop_lat, stop_lng, lat, lng);
                    if (min_dist < 0 || dist < min_dist) {
                        min_dist = dist;
                        min_stop = stop;
                    }
            }
        }
        return min_stop;
    }

	public static ArrayList<HashMap> getDirections(double lat_i, double lng_i,
                                             double lat_f, double lng_f) throws IOException {
        ArrayList<HashMap> routes = TransLocAPI.getRoutes("100");
        ArrayList<HashMap> stops  = TransLocAPI.getStops("100");

        HashMap f_stop = getNearestStop(lat_f, lng_f);

        ArrayList<HashMap> f_routes = new ArrayList<HashMap>();
        for (Integer route : (ArrayList<Integer>)f_stop.get("routes")) {
            for (HashMap temp_route : routes) {
                if (temp_route.get("route_id") == route)
                    f_routes.add(temp_route);
            }
        }

        ArrayList<HashMap> i_stops = new ArrayList<HashMap>();
        for (HashMap route : f_routes)
            i_stops.add(getNearestStopOnRoute(route, lat_i, lng_i));

        double min_dist = -1;
        HashMap i_stop = new HashMap();
        for (HashMap stop : i_stops) {
            double dist = latLngDist(lat_i, lng_i,
                                     Double.valueOf((String)((HashMap)stop.get("location")).get("lat")).doubleValue(),
                                     Double.valueOf((String)((HashMap)stop.get("location")).get("lng")).doubleValue());
            if (min_dist < 0 || dist < min_dist) {
                min_dist = dist;
                i_stop = stop;
            }
        }

        HashMap route = new HashMap();
        for (HashMap temp_route : routes) {
            if ( (((ArrayList)i_stop.get("routes")).contains(temp_route.get("route_id")))
              && (((ArrayList)f_stop.get("routes")).contains(temp_route.get("route_id")))) {
                route = temp_route;
                break;
            }
        }
        ArrayList<HashMap> directions = new ArrayList<HashMap>();
        directions.add(i_stop);
        directions.add(route);
        directions.add(f_stop);
        return directions;
    }

	public static void main(String[] args) {
		return;
	}
}