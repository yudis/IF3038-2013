package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Tugas;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // /rest/tugas/delete/[id]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/delete/" + idTugas);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public Tugas getTugas(int idTugas, boolean retTags, boolean retAttachment, boolean retAssignees) {
        // GET
        // /rest/tugas/[id]/[0-1/true/false]/[0-1/true/false]/[0-1/true/false]

        Tugas tugas = null;
        try {
            HttpURLConnection htc = getConnection("rest/tugas/" + idTugas + "/" + retTags + "/" + retAttachment + "/" + retAssignees);
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
        // /rest/tugas/isu/[idTugas]/[lastRequest]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/isu/" + idTugas + "/" + lastRequest);
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
        // /rest/tugas/isa/[id]        

        try {
            HttpURLConnection htc = getConnection("rest/tugas/isa/" + idTugas);
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
        // /rest/tugas/isp/[id]/[username]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/isp/" + idTugas + "/" + username);
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
        // /rest/tugas/isass/[id]/[username]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/isass/" + idTugas + "/" + username);
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
        // /rest/tugas/utime/[idTugas]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/utime/" + idTugas);
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
        // /rest/tugas/sets/[idTugas]/[0-1/true/false]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/sets/" + idTugas + "/" + status);
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
        // /rest/tugas/setd/[idTugas]/[yyyy]/[mm]/[dd]

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(deadline);
            HttpURLConnection htc = getConnection("rest/tugas/setd/" + idTugas + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DATE));
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int addAssignee(int idTugas, String username) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO `assignees`(`id_tugas`, `username`) VALUES (?, ?);");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, username);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int removeAssignee(int idTugas, String username) {
        // DELETE
        // /rest/tugas/remove/assignee/[id]/[username]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/remove/assignee/" + idTugas + "/" + username);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public List<String> getSuggestionAssignees(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/assignee/[id]/[keyword]/10
        List<String> result = new ArrayList<String>();
        
        try {
            HttpURLConnection htc = getConnection("rest/tugas/suggestion/assignee/" + idTugas + "/" + URLEncoder.encode(keyword, "UTF-8") + "/" + limit);
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

    public int addTag(int idTugas, String tag) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO `tags`(`id_tugas`, `tag`) VALUES (?,?); ");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, tag);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addAttachment(int idTugas, String name, String filename, String type) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO `attachments`(`id_tugas`, `name`, `filename`, `type`) VALUES (?,?,?,?); ");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, filename);
            preparedStatement.setString(4, type);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int removeTag(int idTugas, String tag) {
        // DELETE
        // /rest/tugas/remove/tag/[id_tugas]/[tag]

        try {
            HttpURLConnection htc = getConnection("rest/tugas/remove/tag/" + idTugas + "/" + tag);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public List<String> getSuggestionTags(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/tag/[id]/[keyword]/10

        List<String> result = new ArrayList<String>();

        try {
            HttpURLConnection htc = getConnection("rest/tugas/suggestion/tag/" + idTugas + "/" + URLEncoder.encode(keyword, "UTF-8") + "/" + limit);
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

    public int addTugas(String nama, java.sql.Date deadline, String pemilik, int idKategori) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO tugas(nama, tgl_deadline, pemilik, id_kategori) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, nama);
            preparedStatement.setDate(2, deadline);
            preparedStatement.setString(3, pemilik);
            preparedStatement.setInt(4, idKategori);

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Tugas> getAllTugas() {
        // GET
        // /rest/tugas/

        ArrayList<Tugas> result = null;
        Tugas tugas ;
        try {
            HttpURLConnection htc = getConnection("rest/tugas/");
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                tugas = new Tugas();
                tugas.fromJsonObject(ja.getJSONObject(i));
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
        
        ArrayList<Tugas> result = null;
        Tugas tugas;
        try {
            HttpURLConnection htc = getConnection("rest/tugas/username/" + username);
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                tugas = new Tugas();
                tugas.fromJsonObject(ja.getJSONObject(i));
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
        
        Tugas tugas;
        ArrayList<Tugas> result = new ArrayList<Tugas>();
        try {
            HttpURLConnection htc = getConnection("rest/tugas/search/" + URLEncoder.encode(keyword, "UTF-8") + "/" + start + "/" + limit);
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                tugas = new Tugas();
                tugas.fromJsonObject(ja.getJSONObject(i));
                result.add(tugas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
