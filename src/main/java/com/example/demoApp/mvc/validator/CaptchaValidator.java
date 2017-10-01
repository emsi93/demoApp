package com.example.demoApp.mvc.validator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@ConfigurationProperties(prefix="application")
@Data
@Component("captchaValidator")
@Scope("singleton")
public class CaptchaValidator {

    @Value("${recaptcha.url}")
    private String url;

    @Value("${recaptcha.secret-key}")
    private String secretKey;


    private final static String USER_AGENT = "USER_AGENT";

    public boolean verify(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try{
            URL obj = new URL(url);
            HttpsURLConnection con = getHttpsURLConnection(obj);
            String postParams = "secret=" + secretKey + "&response="
                    + gRecaptchaResponse;
            sendPostRequest(con ,postParams);
            StringBuffer response = getResponse(con);
            JsonObject jsonObject = parseJsonResponse(response);
            return jsonObject.getBoolean("success");
        }catch(Exception e){
            return false;
        }
    }

    private StringBuffer getResponse(HttpsURLConnection con) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    private JsonObject parseJsonResponse(StringBuffer response) {
        JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

    private void sendPostRequest(HttpsURLConnection con, String postParams) throws IOException {
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

    }

    private HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        return con;
    }
}
