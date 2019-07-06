package service;

import netscape.javascript.JSObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlertsAddressAPICall {
    private String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
    private String apiKey = "API KEY";
    private final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = LogManager.getLogger(AlertsAddressAPICall.class);

    public String getAddress(double lat, double lng) {
        String responseToReturn="Could Not find precise Address";
        String finalURL = baseURL + lat + "," + lng + "&key=" + apiKey;
        try {
            URL obj = new URL(finalURL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            LOGGER.info("\nSending 'GET' request to URL : " + finalURL);
            LOGGER.info("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            JSONObject json= new JSONObject(response.toString());
            JSONArray results=json.getJSONArray("results");
            responseToReturn= (String) results.getJSONObject(0).get("formatted_address");
            LOGGER.info(responseToReturn);
        } catch (Exception e) {
            LOGGER.error("Trying to call API",e);
        }
        return responseToReturn;
    }
}
