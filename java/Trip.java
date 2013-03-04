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


public class Trip {

	public static HashMap I_STOP;
	public static HashMap ROUTE;
	public static HashMap F_STOP;
	public static String  I_EST;
	public static String  F_EST;

	public Trip(HashMap i_stop, HashMap route, HashMap f_stop) throws IOException {
		I_STOP = i_stop;
		ROUTE = route;
		F_STOP = f_stop;
		this.getEstimates();
	}

	public static void getEstimates() throws IOException {
		String id = TransLocAPI.getAgency("uchicago");
		ArrayList<HashMap> iEst = TransLocAPI.getEstimate(id, (String)ROUTE.get("route_id"),
		     											      (String)I_STOP.get("stop_id"));
		I_EST = (String)(iEst.get(0)).get("arrival_at");

		ArrayList<HashMap> fEst = TransLocAPI.getEstimate(id, (String)ROUTE.get("route_id"),
			   											      (String)F_STOP.get("stop_id"));
		for (HashMap est : fEst) {
			if (est.get("vehicle_id") == (iEst.get(0)).get("vehicle_id"))
				if (((String)est.get("arrival_at")).compareTo((String)(iEst.get(0)).get("arrival_at")) < 0) {
					F_EST = (String)est.get("arrival_at");
					break;
				}
		}
		return;
	}

	public static void main(String[] args) {
		return;
	}
}