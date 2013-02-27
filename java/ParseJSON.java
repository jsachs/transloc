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
import com.google.gson.JsonObject;

public class ParseJSON {

	public static void parseAgency(String obj) {

        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        System.out.println("gson.toJson: "  + gson.toJson(obj));
        JsonObject json2 = parser.parse("{\"b\":\"c\"}").getAsJsonObject();
        System.out.println("json2: " + json2);
        JsonObject json = parser.parse(gson.toJson(obj)).getAsJsonObject();
        System.out.println("json: " + json);

	}

    public static void main(String[] args) throws IOException {

    	parseAgency("{'foo': bar}");

    }

}