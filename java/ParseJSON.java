// Helper class for parsing various JSON objects

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ParseJSON {

	public static String convertString(String obj) {

    	Gson gson = new Gson();
    	String json = gson.toJson(obj);
    	return json;
	}

	public static void getKey(String json){

		JsonParser jsonParser = new JsonParser();
		JsonArray userArray = jsonParser.parse(json).getAsJsonArray();
		System.out.println(userArray);

		return;
	}

    public static void main(String[] args) throws IOException {

    	getKey(convertString("{\"rate limit\": 10}"));

    }

}