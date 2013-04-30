package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Tugas;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TugasDao extends DataAccessObject {

    public int deleteTugas(int idTugas) {
        // DELETE
        // /rest/tugas/[id]
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas);
            htc.setRequestMethod("DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

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

    public boolean isUpdated(int idTugas, long lastRequest) {
        // GET
        // /rest/tugas/[idTugas]/u/[lastRequest]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/u/" + lastRequest);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Boolean.parseBoolean(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public boolean isAvailable(int idTugas) {
        // GET
        // /rest/tugas/[id]/0        

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/0");
            htc.setRequestMethod("GET");

            Tugas tmp = new Tugas();
            tmp.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean isPemilik(int idTugas, String username) {
        // GET
        // /rest/tugas/14/pemilik/edwardsp

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/pemilik/" + username);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Boolean.parseBoolean(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean isAssignee(int idTugas, String username) {
        // GET
        // /rest/tugas/[14]/assignee/[edwardsp]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/assignee/" + username);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Boolean.parseBoolean(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public int updateTimestamp(int idTugas) {
        // POST
        // /rest/tugas/[idTugas]/timestamp

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/timestamp");
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int setStatus(int idTugas, boolean status) {
        // POST
        // /rest/tugas/[idTugas]/[0-1]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/" + (status ? 1 : 0));
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int setDeadline(int idTugas, java.sql.Date deadline) {
        // POST
        // /rest/tugas/[idTugas]/deadline/[yyyy]/[mm]/[dd]

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(deadline);
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/deadline/" + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DATE));
            htc.setRequestMethod("POST");
            
            System.out.println(htc.getURL());

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int removeAssignee(int idTugas, String username) {
        // DELETE
        // /rest/tugas/[1]/assignee/[edwardsp]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/assignee/" + username);
            htc.setRequestMethod("DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public List<String> getSuggestionAssignees(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/assignees/[23]/[p%25]/[10]

        List<String> result = new ArrayList<String>();

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/suggestion/assignees/" + idTugas + "/" + URLEncoder.encode(keyword, "UTF-8") + "/" + limit);
            htc.setRequestMethod("GET");
            
            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                result.add(jArray.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public int removeTag(int idTugas, String tag) {
        // DELETE
        // /rest/tugas/[1]/tag/[edwardsp]

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/tag/" + tag);
            htc.setRequestMethod("DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public List<String> getSuggestionTags(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/tags/23/p%25/10

        List<String> result = new ArrayList<String>();

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/suggestion/tags/" + idTugas + "/" + URLEncoder.encode(keyword, "UTF-8") + "/" + limit);
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                result.add(jArray.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<Tugas> getAllTugas() {
        // GET
        // /rest/tugas/

        ArrayList<Tugas> result = new ArrayList<Tugas>();

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/");
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Tugas tugas = new Tugas();
                tugas.fromJsonObject(jArray.getJSONObject(i));
                result.add(tugas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public Collection<Tugas> getTugas(String username) {
        // GET
        // /rest/tugas/username/[username]

        ArrayList<Tugas> result = new ArrayList<Tugas>();
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/username/" + username);
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Tugas tugas = new Tugas();
                tugas.fromJsonObject(jArray.getJSONObject(i));
                result.add(tugas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public Collection<Tugas> getTugasSearch(String keyword, int start, int limit) {
        // GET
        // /rest/tugas/search/[keyword]/[start]/[limit]

        ArrayList<Tugas> result = new ArrayList<Tugas>();
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/search/" + URLEncoder.encode(keyword, "UTF-8") + "/" + start + "/" + limit);
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Tugas tugas = new Tugas();
                tugas.fromJsonObject(jArray.getJSONObject(i));
                result.add(tugas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public int addAssignee(int idTugas, java.lang.String username) {
        id.ac.itb.todolist.soap.tugas.TugasSoap_Service service = new id.ac.itb.todolist.soap.tugas.TugasSoap_Service();
        id.ac.itb.todolist.soap.tugas.TugasSoap port = service.getTugasSoapPort();
        return port.addAssignee(idTugas, username);
    }

    public int addAttachment(int idTugas, java.lang.String name, java.lang.String filename, java.lang.String type) {
        id.ac.itb.todolist.soap.tugas.TugasSoap_Service service = new id.ac.itb.todolist.soap.tugas.TugasSoap_Service();
        id.ac.itb.todolist.soap.tugas.TugasSoap port = service.getTugasSoapPort();
        return port.addAttachment(idTugas, name, filename, type);
    }

    public int addTag(int idTugas, java.lang.String tag) {
        id.ac.itb.todolist.soap.tugas.TugasSoap_Service service = new id.ac.itb.todolist.soap.tugas.TugasSoap_Service();
        id.ac.itb.todolist.soap.tugas.TugasSoap port = service.getTugasSoapPort();
        return port.addTag(idTugas, tag);
    }

    public int addTugas(java.lang.String nama, java.lang.String deadline, java.lang.String pemilik, int idKategori) {
        id.ac.itb.todolist.soap.tugas.TugasSoap_Service service = new id.ac.itb.todolist.soap.tugas.TugasSoap_Service();
        id.ac.itb.todolist.soap.tugas.TugasSoap port = service.getTugasSoapPort();
        return port.addTugas(nama, deadline, pemilik, idKategori);
    }
}
