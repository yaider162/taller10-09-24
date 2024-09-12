package uptc.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;

public class JSONReader {
    public static <T> T readJsonFromUrl(String urlString, Class<T> clazz) {
        T result = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder jsonContent = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                jsonContent.append(output);
                System.out.println(jsonContent);
            }
            conn.disconnect();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapter(Period.class, new PeriodAdapter())
                    .create();
            result = gson.fromJson(jsonContent.toString(), clazz);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}