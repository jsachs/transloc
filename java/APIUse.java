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

package edu.uchicago.mobile.visual_test;



public class APIUse {

    public boolean need_switch(double lat, double longitude, String end_address) {

        //true if you need to switch, false otherwise
        return false;
    }

    public String[] travel_info(double lat, double longitude, String end_address) {

        String ret[]={"a", "b"};

        //return start name, route name, end name

        return ret;

    }

    public double[] end_loc(String end_name) {

        //return the latitude and longitude

        double ret[]={1, 2};

        return ret;

    }

    public int arrival_time(String route, String stop) {
    
        //return time till arrival

        return 0;

    }

    public String[] find_shuttle(double lat, double longitude, String shuttle) {

        //return closest stop, time estimate

        String ret[]={"a", "b"};

        return ret;

    }
}