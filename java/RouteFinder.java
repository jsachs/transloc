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

	public static HashMap getNearestStop(double lat, double lng);

	public static HashMap getNearestStopOnRoute(HashMap route, double lat, double lng);

	public static String[] getDirections(double lat_i, double lng_i,
                                             double lat_f, double lng_f);

	public static void main(String[] args) {
		return;
	}
}