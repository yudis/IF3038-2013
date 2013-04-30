
package id.ac.itb.todolist.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

public class URLconn {
    public JSONArray rest(String s) throws Exception {
        //s :/<command>
        URL link = new URL("http://dolist.ap01.aws.af.cm/rest/"+s);
        URLConnection yc = link.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;
        JSONObject j=new JSONObject();
        JSONArray ja = new JSONArray();
        int i=0;
        while ((inputLine = in.readLine()) != null) 
        { 
            System.out.println(inputLine);
            j=j.getJSONObject(inputLine);
            ja.put(i, j);
        }
        in.close();
        return ja;
    }
}
