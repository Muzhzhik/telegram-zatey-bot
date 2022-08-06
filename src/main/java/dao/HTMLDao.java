package dao;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class HTMLDao implements DataDAO{

    public HTMLDao(URL url) throws IOException {
        URLConnection connection = url.openConnection();

        try(InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
        {
            String html = new String(input.readAllBytes());
            System.out.println(html);
        }
    }

    @Override
    public void getData() {

    }
}
