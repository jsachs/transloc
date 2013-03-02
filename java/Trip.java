// Class for storing information about a user's route

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

public class Trip {

	public HashMap I_STOP;
	public HashMap ROUTE;
	public HashMap F_STOP;
	public String  I_EST;
	public String  F_EST;

	public Trip(HashMap i_stop, HashMap route, HashMap f_stop) {
		I_STOP = i_stop;
		ROUTE = route;
		F_STOP = f_stop;
	}

	public static void getEstimates() {
		ArrayList<HashMap> iEst = TransLocAPI.getEstimate(this.ROUTE.get("route_id"),
		     											  this.I_STOP.get("stop_id"));
		this.I_EST = (iEst.get(0)).get("arrival_at");

		ArrayList<HashMap> fEst = TransLocAPI.getEstimate(this.ROUTE.get("route_id"),
			   											  this.F_STOP.get("stop_id"));
		for (HashMap est : fEst) {
			if est.get("vehicle_id") == (iEst.get(0)).get("vehicle_id")
				if est.get("arrival_at") > (iEst.get(0)).get("arrival_at") {
					this.F_EST = est.get("arrival_at");
					break;
				}
		}
		return;
	}

	public static void main(String[] args) {
		return;
	}
}