/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONTokener;

/**
 *
 * @author Felix
 */
public class SearchAutoCompleteDao extends DataAccessObject {
        
    public ArrayList<String> getSearchAC(String q, String filter){
    // GET
    // rest/searchac/[username]/[filter]
        ArrayList<String> result = new ArrayList<String>();    
        try {
            HttpURLConnection htc = getConnection("rest/searchac/"+URLEncoder.encode(q, "UTF-8")+"/"+URLEncoder.encode(filter, "UTF-8"));
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0 ;i < ja.length(); i++) {
                result.add(ja.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
}
