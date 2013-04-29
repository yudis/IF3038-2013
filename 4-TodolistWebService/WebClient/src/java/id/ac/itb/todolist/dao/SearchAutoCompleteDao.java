package id.ac.itb.todolist.dao;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONTokener;

public class SearchAutoCompleteDao extends DataAccessObject {

    public ArrayList<String> getSearchAC(String q, String filter) {
        // GET
        // rest/searchac/username/w
        ArrayList<String> result = new ArrayList<String>();
        
       try {
            HttpURLConnection htc = getHttpURLConnection("/rest/searchac/"+URLEncoder.encode(q, "UTF-8")+"/"+URLEncoder.encode(filter, "UTF-8"));
            htc.setRequestMethod("GET");

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
