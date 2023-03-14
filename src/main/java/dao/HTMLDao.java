package dao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import entity.SearchResult;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HTMLDao implements DataDAO {

    private final URL url;

    public HTMLDao(URL url) {
        this.url = url;
    }

    @Override
    public SearchResult getData() {
        SearchResult result = null;
        BufferedReader br = null;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            String html = sb.toString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            result = mapper.readValue(html, SearchResult.class);
            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (con != null)
                con.disconnect();
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
        return result;
    }
}
