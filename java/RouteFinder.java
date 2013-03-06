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

import org.json.*;

// TODO: error message if the Big 4 aren't running.

public class RouteFinder {

    public static ArrayList<Integer> ROUTE_LIST = new ArrayList<Integer>() {{ 
                                                add(8000576);  // North
                                                add(8000548);  // Central
                                                add(8000560);  // East
                                                add(8000580);  // South
                                            }};

	public static double latLngDist(double x1, double y1, double x2, double y2) {
		double dist = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
		return dist;
	}

	public static HashMap getNearestStop(double lat, double lng) throws IOException, JSONException {
        String agency = TransLocAPI.getAgency("uchicago");
        ArrayList<HashMap> routes = TransLocAPI.getRoutes(agency);
        ArrayList<HashMap> stops  = TransLocAPI.getStops(agency);
        double min_dist = -1;
        HashMap min_stop = new HashMap();
        
        ArrayList<Integer> route_ids = new ArrayList<Integer>();
        for (HashMap route: routes) {
            Integer rid = Integer.parseInt((String)route.get("route_id"));
            if (ROUTE_LIST.contains(rid))
                route_ids.add(rid);
        }

        for (HashMap stop : stops) {
            ArrayList<Integer> route_list = (ArrayList<Integer>)stop.get("routes");
            for (Integer id : route_list) {
                if (route_ids.contains(id)) {
                    double stop_lat = ((JSONObject)stop.get("location")).getDouble("lat");
                    double stop_lng = ((JSONObject)stop.get("location")).getDouble("lng");
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

	public static HashMap getNearestStopOnRoute(HashMap route, double lat, double lng) throws IOException, JSONException {
        String agency = TransLocAPI.getAgency("uchicago");
        ArrayList<HashMap> stops  = TransLocAPI.getStops(agency);
        double min_dist = -1;
        HashMap min_stop = new HashMap();

        for (HashMap stop : stops) {
            if (((ArrayList<Integer>)stop.get("routes")).contains(Integer.parseInt((String)route.get("route_id")))) {
                    double stop_lat = ((JSONObject)stop.get("location")).getDouble("lat");
                    double stop_lng = ((JSONObject)stop.get("location")).getDouble("lng");
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
                                             double lat_f, double lng_f) throws IOException, JSONException {
        String agency = TransLocAPI.getAgency("uchicago");
        ArrayList<HashMap> routes = TransLocAPI.getRoutes(agency);
        ArrayList<HashMap> stops  = TransLocAPI.getStops(agency);

        HashMap f_stop = getNearestStop(lat_f, lng_f);

        ArrayList<HashMap> f_routes = new ArrayList<HashMap>();
        for (Integer route : (ArrayList<Integer>)f_stop.get("routes")) {
            for (HashMap temp_route : routes) {
                if ((Integer.parseInt((String)temp_route.get("route_id"))) == route)
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
                                     ((JSONObject)stop.get("location")).getDouble("lat"),
                                     ((JSONObject)stop.get("location")).getDouble("lng"));
            if ((min_dist < 0) || (dist < min_dist)) {
                min_dist = dist;
                i_stop = stop;
            }
        }
        System.out.println(i_stop.get("routes"));
        System.out.println(f_stop.get("routes"));
        HashMap route = new HashMap();
        for (HashMap temp_route : routes) {
            if ( (((ArrayList<Integer>)i_stop.get("routes")).contains(Integer.parseInt((String)temp_route.get("route_id"))))
              && (((ArrayList<Integer>)f_stop.get("routes")).contains(Integer.parseInt((String)temp_route.get("route_id"))))
              && ROUTE_LIST.contains(Integer.parseInt((String)temp_route.get("route_id")))) {
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

	public static void main(String[] args) throws IOException, JSONException {
        HashMap stop = getNearestStop(41.791133,-87.598881);
        ArrayList<HashMap> routes = TransLocAPI.getRoutes("100");
        HashMap stop_1 = getNearestStopOnRoute(routes.get(4),41.791133,-87.598881);
        ArrayList<HashMap> direc = getDirections(41.791393,-87.599776,
                                                 41.796664,-87.60438);
        System.out.println(direc);
		return;
	}
}


