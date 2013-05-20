package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Tugas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TugasDao extends DataAccessObject {

    public Tugas getTugas(int idTugas, boolean retTags, boolean retAttachment, boolean retAssignees) {
        // GET
        // /rest/tugas/[id]/[int3bool]

        Tugas tugas = null;
        try {
            int val = 0;
            if (retTags) {
                val |= 4;
            }
            if (retAttachment) {
                val |= 2;
            }
            if (retAssignees) {
                val |= 1;
            }

            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/" + val);
            htc.setRequestMethod("GET");
            
            Tugas tmp = new Tugas();
            tmp.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));

            tugas = tmp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tugas;
    }
    
    public int setStatus(int idTugas, boolean status, Timestamp timestamp) {
        // POST
        // /rest/tugas/setstatus/[idTugas]/[0-1]/[timestamp(long)]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/setstatus/" + idTugas + "/" + (status ? 1 : 0) + "/" + timestamp.getTime());
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }
    
    public HashMap<Integer, Long> getAllTugasbyUser(String username) {
        // GET
        // /rest/tugas/tgsbyuser/[username]
        
        HashMap<Integer, Long> result = null;
        
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/tgsbyuser/" + username);
            htc.setRequestMethod("GET");

            result = new HashMap<>();
            JSONObject jObject = new JSONObject(new JSONTokener(new InputStreamReader(htc.getInputStream())));
            for (Iterator i = jObject.keys(); i.hasNext();) {
                String ids = (String) i.next();
                int id = Integer.parseInt(ids);
                result.put(id, jObject.getLong(ids)) ;
            }
        } catch (IOException | JSONException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        
        return result;
    } 
}
