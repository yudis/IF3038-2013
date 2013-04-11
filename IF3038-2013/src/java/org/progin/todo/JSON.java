package org.progin.todo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kamilersz
 */
public class JSON {
    public static JSONObject Object(HashMap<String, Object> h) {
        JSONObject obj = new JSONObject();
        if (h != null) {
        for (Entry<String, Object> entry : h.entrySet()) {
            try {
                obj.put(entry.getKey(), entry.getValue());
            } catch (JSONException ex) {
                Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        return obj;
    }
    public static JSONArray Array(List<HashMap<String, Object>> l) {
        JSONArray arr = new JSONArray();
        for (Iterator it = l.iterator(); it.hasNext();) {
            HashMap<String, Object> r = (HashMap<String, Object>) it.next();
            arr.put(Object(r));
        }
        return arr;
    }
}
