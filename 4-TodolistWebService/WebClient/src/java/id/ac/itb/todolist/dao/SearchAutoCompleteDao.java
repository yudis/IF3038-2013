package id.ac.itb.todolist.dao;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONTokener;

public class SearchAutoCompleteDao extends DataAccessObject {

    public ArrayList<String> getSearchAC(String q, String filter) {
        // GET
        // rest/searchac/username/w
        ArrayList<String> result = new ArrayList<String>();
        
       try {
            HttpURLConnection htc = getHttpURLConnection("/rest/searchac/"+URLEncoder.encode(filter, "UTF-8")+"/"+URLEncoder.encode(q, "UTF-8"));
            htc.setRequestMethod("GET");

            result=new ArrayList<String>();
            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                result.add(jArray.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//
        return result;
    }
}
