package micdoodle8.mods.galacticraft.core.util;

import org.apache.commons.io.Charsets;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CapeUtil
{

    private static final CapeUtil instance = new CapeUtil();

    public static CapeUtil instance()
    {
        return instance;
    }

    private URLConnection connection;

    private CapeUtil()
    {
        try
        {
            URL jsonUrl = new URL("https://raw.githubusercontent.com/TeamGalacticraft/capes/legacy/capesList.json");
            this.connection = jsonUrl.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getCape(String uuid)
    {
        StringBuilder object = new StringBuilder();
        List<String> lines = readLines(connection);
        for (String line : lines)
        {
            object.append(line);
        }
        JsonElement json = new JsonParser().parse(object.toString());
        JsonObject obj = json.getAsJsonObject();
        try
        {
            return obj.getAsJsonObject("players").get(uuid).getAsString();
        } catch (Exception e)
        {
            return "none";
        }
    }

    private List<String> readLines(final URLConnection connection)
    {
        final List<String> list = new ArrayList<>();
        try (InputStream inputStream = connection.getInputStream())
        {
            final InputStreamReader reader = new InputStreamReader(inputStream, Charsets.toCharset(StandardCharsets.UTF_8));
            final BufferedReader bufReader = new BufferedReader(reader);
            String line;
            while ((line = bufReader.readLine()) != null)
            {
                list.add(line);
            }
            return list;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
