package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Attachment;
import id.ac.itb.todolist.model.Category;
import id.ac.itb.todolist.model.Tugas;
import id.ac.itb.todolist.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TugasDao extends DataAccessObject {

    public int deleteTugas(int idTugas) {
        // DELETE
        // /rest/tugas/[id]

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM `tugas` WHERE id=?;");
            preparedStatement.setInt(1, idTugas);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Tugas getTugas(int idTugas, boolean retTags, boolean retAttachment, boolean retAssignees) {
        // GET
        // /rest/tugas/[id]/[int3bool]

        Tugas tugas = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT t.`id` AS `id`, t.`nama` AS `nama`, `tgl_deadline`,  `status` , t.`last_mod` AS `last_mod`, t.`pemilik` AS `pemilik_username`, u.`email` AS `pemilik_email`, u.`password` AS `pemilik_password`, u.`full_name` AS `pemilik_full_name`, u.`tgl_lahir` AS `pemilik_tgl_lahir`, u.`avatar` AS `pemilik_avatar`, c.`id` AS `kategori_id`, c.`nama` AS `kategori_nama`, c.`last_mod` AS `kategori_last_mod` FROM `categories` c, `tugas` t, `users` u WHERE t.`id` = ? AND c.`id` = t.`id_kategori` AND u.`username` = t.`pemilik`;");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
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

                if (retTags) {
                    tugas.setTags(getTags(idTugas));
                }

                if (retAttachment) {
                    tugas.setAttachments(getAttachments(idTugas));
                }

                if (retAssignees) {
                    tugas.setAssignees(getAssignees(idTugas));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tugas;
    }

    private Collection<User> getAssignees(int idTugas) {
        ArrayList<User> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT a.`username` AS `username`, u.`full_name` AS `full_name`, u.`avatar` AS `avatar`, u.`email` AS `email`, u.`password` AS `password`, u.`tgl_lahir` AS `tgl_lahir` FROM `assignees` a, `users` u WHERE a.`id_tugas`=? AND a.`username`=u.`username`;");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<User>();
            while (rs.next()) {
                User item = new User();
                item.setUsername(rs.getString("username"));
                item.setEmail(rs.getString("email"));
                item.setHashedPassword(rs.getString("password"));
                item.setFullName(rs.getString("full_name"));
                item.setTglLahir(rs.getDate("tgl_lahir"));
                item.setAvatar(rs.getString("avatar"));

                result.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Collection<Attachment> getAttachments(int idTugas) {
        ArrayList<Attachment> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT `id_attachment`, `id_tugas`, `name`, `filename`, `type` FROM attachments WHERE id_tugas=?");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<Attachment>();
            while (rs.next()) {
                Attachment item = new Attachment();
                item.setIdAttachment(rs.getInt("id_attachment"));
                item.setIdTugas(rs.getInt("id_tugas"));
                item.setName(rs.getString("name"));
                item.setFilename(rs.getString("filename"));
                item.setType(rs.getString("type"));

                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Collection<String> getTags(int idTugas) {
        ArrayList<String> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT tag FROM tags WHERE id_tugas=?");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<String>();
            while (rs.next()) {
                result.add(rs.getString("tag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isUpdated(int idTugas, long lastRequest) {
        // GET
        // /rest/tugas/[idTugas]/u/[lastRequest]

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM `tugas` WHERE `id` = ? AND `last_mod` > FROM_UNIXTIME(?);");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setLong(2, lastRequest);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isAvailable(int idTugas) {
        // GET
        // /rest/tugas/[id]/0        

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM `tugas` WHERE `id`=? LIMIT 0, 1;");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPemilik(int idTugas, String username) {
        // GET
        // /rest/tugas/14/pemilik/edwardsp

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM `tugas` WHERE `id`=? AND `pemilik`=? LIMIT 0, 1");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAssignee(int idTugas, String username) {
        // GET
        // /rest/tugas/14/assignee/edwardsp

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM `tugas` t INNER JOIN `assignees` a ON t.`id` = a.`id_tugas` WHERE t.`id` = ? AND a.`username` = ?;");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setString(2, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updateTimestamp(int idTugas) {
        // POST
        // /rest/tugas/[idTugas]/timestamp

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE `tugas` SET `last_mod` = CURRENT_TIMESTAMP WHERE `tugas`.`id` = ?;");
            preparedStatement.setInt(1, idTugas);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int setStatus(int idTugas, boolean status) {
        // POST
        // /rest/tugas/[idTugas]/[0-1]

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE `tugas` SET `status` = ? WHERE `id` = ?;");
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, idTugas);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int setStatus(int idTugas, boolean status, Timestamp timestamp) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE `tugas` SET `status` = ?, `last_mod` = ? WHERE `id` = ?;");
            preparedStatement.setBoolean(1, status);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, idTugas);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int setDeadline(int idTugas, java.sql.Date deadline) {
        // POST
        // /rest/tugas/[idTugas]/deadline/[yyyy]/[mm]/[dd]

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE `tugas` SET `tgl_deadline` = ? WHERE `tugas`.`id` = ?;");
            preparedStatement.setDate(1, deadline);
            preparedStatement.setInt(2, idTugas);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
                    prepareStatement("INSERT INTO tugas(nama, tgl_deadline, pemilik, id_kategori) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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
