// Class for pulling JSON from the TransLoc API

import java.io.BufferedReader;
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


public class TransLocAPI {

    private static String TRANSLOC = "http://api.transloc.com/1.1/";
    private TransLocAPI() {}

    /*
    Method to get a list of agencies from TransLoc
    */
    public static String getAgency(String name) throws IOException {
        URL url = new URL(TRANSLOC + "agencies.json");
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
        builder.append(line);
        }

        return builder.toString();
    }

    /*
    Method to get a list of routes from TransLoc
    */
    public static String getRoutes(String id) throws IOException {
        URL url = new URL(TRANSLOC + "routes.json?agencies=" + id);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
        builder.append(line);
        }

        return builder.toString();
    }

    public static String getStops(String id) throws IOException {
        URL url = new URL(TRANSLOC + "stops.json?agencies=" + id);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
        builder.append(line);
        }

        return builder.toString();
    }

    public static String getEstimate(String id, String route, String stop) throws IOException {
        URL url = new URL(TRANSLOC + "arrival-estimate.json?"
                                   + "agencies=" + id
                                   + "&routes="  + route
                                   + "&stops="   + stop);
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
        builder.append(line);
        }

        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        
        String agency = TransLocAPI.getAgency("uchicago");
        System.out.println(agency);

        /* The main shouldn't do anything right now

        Map map = new HashMap();
        map.put("rs_type", new String[]{"standard"});
        map.put("sp", "");

        String response = OMTR_REST.callMethod("Company.GetReportSuites", JSONObject.fromObject(map).toString());
        JSONObject jsonObj = JSONObject.fromObject(response);
        JSONArray jsonArry = JSONArray.fromObject(jsonObj.get("report_suites"));

        for(int i = 0; i < jsonArry.size(); i++) {
            System.out.println("Report Suite ID: " + JSONObject.fromObject(jsonArry.get(i)).get("rsid"));
            System.out.println("Site Title: " + JSONObject.fromObject(jsonArry.get(i)).get("site_title"));
            System.out.println();
        */
        }
}


