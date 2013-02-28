// Class for getting Google Maps walking directions

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

public class Walking {

	public static String[] walkTo(double lat, double lng, HashMap stop);

    public static String[] walkFrom(HashMap stop, double lat, double lng);

	public static void main(String[] args) {
		return;		
	}
}