// Class for pulling JSON from the TransLoc API

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

import groovy.json.JsonSlurper;


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

        String agency = ParseJSON.parseAgency(name, builder.toString());
        return agency;
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
        
        System.out.println(getAgency("uchicago"));

    }
}

class ParseJSON {

    public static String parseAgency(String name, String json) {

        JsonSlurper slurper = new JsonSlurper();
        HashMap parse = (HashMap)slurper.parseText(json);
        ArrayList<HashMap> data = (ArrayList<HashMap>)parse.get("data");

        String ret = "";

        for (HashMap agency : data) {
            if (agency.get("name").equals(name))
                ret = (String)agency.get("agency_id");
        }
        return ret;
    }

    public static void parseRoutes(String json) {

        JsonSlurper slurper = new JsonSlurper();
        HashMap parse = (HashMap)slurper.parseText(json);
        HashMap data = (HashMap)parse.get("data");
        List<HashMap> routes = (List<HashMap>)data.get("100");

        for (HashMap object : routes) {
            System.out.println(object);
        }

    }

    public static void parseStops(String json) {

        JsonSlurper slurper = new JsonSlurper();
        HashMap parse = (HashMap)slurper.parseText(json);
    }

    public static void parseEstimate(String json) {

        JsonSlurper slurper = new JsonSlurper();
        HashMap parse = (HashMap)slurper.parseText(json);
    }

    public static void main(String[] args) throws IOException {

    }

}


