package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Attachment;
import id.ac.itb.todolist.model.Category;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
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
            if (retTags) val |= 4;
            if (retAttachment) val |= 2;
            if (retAssignees) val |= 1;
            
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
            HttpURLConnection htc = getHttpURLConnection("/rest/tugas/" + idTugas + "/" + (status ? 0 : 1));
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
            
            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return -1;
    }

    public int addAssignee(int idTugas, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int removeAssignee(int idTugas, String username) {
        // DELETE
        // /rest/tugas/assignee/edwardsp

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM `assignees` WHERE `id_tugas` = ? AND `username` = ?;");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, username);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getSuggestionAssignees(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/assignees/23/p%25/10

        List<String> result = new ArrayList<String>();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT `username` FROM `users` WHERE `username` NOT IN (SELECT `username` FROM assignees WHERE `id_tugas`=? UNION SELECT `pemilik` AS `username` FROM `tugas` WHERE `id`=?) AND `username` LIKE ? LIMIT 0, ?;");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setInt(2, idTugas);
            preparedStatement.setString(3, keyword);
            preparedStatement.setInt(4, limit);

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        // /rest/tugas/tag/edwardsp

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM `tags` WHERE `id_tugas`=? AND `tag`=?;");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, tag);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getSuggestionTags(int idTugas, String keyword, int limit) {
        // GET
        // /rest/tugas/suggestion/tags/23/p%25/10

        List<String> result = new ArrayList<String>();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT `tag` FROM `tags` WHERE `id_tugas` <> ? AND `tag` LIKE ? LIMIT 0, ?");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, keyword);
            preparedStatement.setInt(3, limit);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("tag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        Tugas tugas = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT t.`id` AS `id`, t.`nama` AS `nama`, `tgl_deadline`,  `status` , t.`last_mod` AS `last_mod`, t.`pemilik` AS `pemilik_username`, u.`email` AS `pemilik_email`, u.`password` AS `pemilik_password`, u.`full_name` AS `pemilik_full_name`, u.`tgl_lahir` AS `pemilik_tgl_lahir`, u.`avatar` AS `pemilik_avatar`, c.`id` AS `kategori_id`, c.`nama` AS `kategori_nama`, c.`last_mod` AS `kategori_last_mod` FROM `categories` c, `tugas` t, `users` u WHERE  c.`id` = t.`id_kategori` AND u.`username` = t.`pemilik`;");

            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<Tugas>();
            while (rs.next()) {
                tugas = new Tugas();

                tugas.setId(rs.getInt("id"));
                tugas.setNama(rs.getString("nama"));
                tugas.setTglDeadline(rs.getDate("tgl_deadline"));
                tugas.setStatus(rs.getBoolean("status"));
                tugas.setLastMod(rs.getTimestamp("last_mod"));

                User pemilik = new User();
                pemilik.setUsername(rs.getString("pemilik_username"));
                pemilik.setEmail(rs.getString("pemilik_email"));
                pemilik.setHashedPassword(rs.getString("pemilik_password"));
                pemilik.setFullName(rs.getString("pemilik_full_name"));
                pemilik.setTglLahir(rs.getDate("pemilik_tgl_lahir"));
                pemilik.setAvatar(rs.getString("pemilik_avatar"));
                tugas.setPemilik(pemilik);

                Category kategori = new Category();
                kategori.setId(rs.getInt("kategori_id"));
                kategori.setNama(rs.getString("kategori_nama"));
                kategori.setLastMod(rs.getTimestamp("kategori_last_mod"));
                tugas.setKategori(kategori);

                tugas.setTags(getTags(rs.getInt("id")));
                tugas.setAttachments(getAttachments(rs.getInt("id")));
                tugas.setAssignees(getAssignees(rs.getInt("id")));

                result.add(tugas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<Tugas> getTugas(String username) {
        // GET
        // /rest/tugas/username/[username]

        ArrayList<Tugas> result = null;
        Tugas tugas = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT t.`id` AS `id`, t.`nama` AS `nama`, `tgl_deadline`,  `status` , t.`last_mod` AS `last_mod`, t.`pemilik` AS `pemilik_username`, u.`email` AS `pemilik_email`, u.`password` AS `pemilik_password`, u.`full_name` AS `pemilik_full_name`, u.`tgl_lahir` AS `pemilik_tgl_lahir`, u.`avatar` AS `pemilik_avatar`, c.`id` AS `kategori_id`, c.`nama` AS `kategori_nama`, c.`last_mod` AS `kategori_last_mod` FROM `categories` c, `tugas` t, `users` u WHERE  c.`id` = t.`id_kategori` AND u.`username` = t.`pemilik` ORDER BY `status`,`kategori_id`;");

            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<Tugas>();
            while (rs.next()) {
                tugas = new Tugas();

                tugas.setId(rs.getInt("id"));
                tugas.setNama(rs.getString("nama"));
                tugas.setTglDeadline(rs.getDate("tgl_deadline"));
                tugas.setStatus(rs.getBoolean("status"));
                tugas.setLastMod(rs.getTimestamp("last_mod"));

                User pemilik = new User();
                pemilik.setUsername(rs.getString("pemilik_username"));
                pemilik.setEmail(rs.getString("pemilik_email"));
                pemilik.setHashedPassword(rs.getString("pemilik_password"));
                pemilik.setFullName(rs.getString("pemilik_full_name"));
                pemilik.setTglLahir(rs.getDate("pemilik_tgl_lahir"));
                pemilik.setAvatar(rs.getString("pemilik_avatar"));
                tugas.setPemilik(pemilik);

                Category kategori = new Category();
                kategori.setId(rs.getInt("kategori_id"));
                kategori.setNama(rs.getString("kategori_nama"));
                kategori.setLastMod(rs.getTimestamp("kategori_last_mod"));
                tugas.setKategori(kategori);

                tugas.setTags(getTags(rs.getInt("id")));
                tugas.setAttachments(getAttachments(rs.getInt("id")));
                tugas.setAssignees(getAssignees(rs.getInt("id")));

                if (isAssignee(tugas.getId(), username) || (isPemilik(tugas.getId(), username))) {
                    result.add(tugas);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Collection<Tugas> getTugasSearch(String keyword, int start, int limit) {
        // GET
        // /rest/tugas/search/[keyword]/[start]/[limit]

        Tugas tugas = null;
        ArrayList<Tugas> result = new ArrayList<Tugas>();
        String qry = "SELECT DISTINCT t.`id` AS `id`, t.`nama` AS `nama`, `tgl_deadline`,  `status` , t.`last_mod` AS `last_mod`, t.`pemilik` AS `pemilik_username`, u.`email` AS `pemilik_email`, u.`password` AS `pemilik_password`, u.`full_name` AS `pemilik_full_name`, u.`tgl_lahir` AS `pemilik_tgl_lahir`, u.`avatar` AS `pemilik_avatar`, c.`id` AS `kategori_id`, c.`nama` AS `kategori_nama`, c.`last_mod` AS `kategori_last_mod` FROM `categories` c, `tugas` t, `users` u, `tags` teg WHERE (t.`nama` LIKE '%" + keyword + "%' OR teg.`tag` LIKE '%" + keyword + "%') AND c.`id` = t.`id_kategori` AND u.`username` = t.`pemilik` AND teg.`id_tugas` = t.`id` ORDER BY kategori_id LIMIT " + start + ", " + limit + ";";
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(qry);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tugas = new Tugas();

                tugas.setId(rs.getInt("id"));
                tugas.setNama(rs.getString("nama"));
                tugas.setTglDeadline(rs.getDate("tgl_deadline"));
                tugas.setStatus(rs.getBoolean("status"));
                tugas.setLastMod(rs.getTimestamp("last_mod"));

                User pemilik = new User();
                pemilik.setUsername(rs.getString("pemilik_username"));
                pemilik.setEmail(rs.getString("pemilik_email"));
                pemilik.setHashedPassword(rs.getString("pemilik_password"));
                pemilik.setFullName(rs.getString("pemilik_full_name"));
                pemilik.setTglLahir(rs.getDate("pemilik_tgl_lahir"));
                pemilik.setAvatar(rs.getString("pemilik_avatar"));
                tugas.setPemilik(pemilik);

                Category kategori = new Category();
                kategori.setId(rs.getInt("kategori_id"));
                kategori.setNama(rs.getString("kategori_nama"));
                kategori.setLastMod(rs.getTimestamp("kategori_last_mod"));
                tugas.setKategori(kategori);

                tugas.setTags(getTags(tugas.getId()));
                tugas.setAttachments(getAttachments(tugas.getId()));
                tugas.setAssignees(getAssignees(tugas.getId()));
                result.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
