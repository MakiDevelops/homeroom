package org.orgst;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
// YOU ARE NOT SUPPOSED TO READ THIS, THIS IS SOMETHING THAT IS BARELY USED
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DLVid {
    public static void main(String[] args) {
        dl();
    }
    public static Map<String, String> dl() {
        try {
            URL url = new URI("https://api.github.com/repos/MakiDevelops/homeroom/contents/videos").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            Map<String, String> resp = new HashMap<>();
            JsonElement root = new JsonParser().parse(reader);
            for (JsonElement el : root.getAsJsonArray()) {
                resp.put(el.getAsJsonObject().get("name").getAsString(), el.getAsJsonObject().get("download_url").getAsString());
            }
            reader.close();
            if (!new File("tmp").exists()){File tmp = new File("tmp"); tmp.mkdirs();}
            if (!new File("tmp/videos").exists()){File tmpvids = new File("tmp/videos"); tmpvids.mkdirs();}
            for (Map.Entry<String, String> entry : resp.entrySet()) {
                try (InputStream in = new URL(entry.getValue()).openStream()) {
                    Files.copy(in, Paths.get("tmp/videos/" + entry.getKey()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            System.out.println(resp.toString());
            return resp;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
