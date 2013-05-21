/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import model.Tugas;

/**
 *
 * @author TOSHIBA
 */
public class DbOperation {

    private Connection connection;

    public DbOperation() {
        try {
            Properties prop = new Properties();
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_147_13510059", "progin", "progin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkLogin(String username, String password) {
        boolean ret = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username=? and password=MD5(?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            ret = rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Tugas> getTugas(String username) {
        List<Tugas> tasks = new ArrayList<Tugas>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tugas WHERE pemilik=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Tugas tugas = new Tugas();
                String id = rs.getString("id");
                
                tugas.setId(Integer.parseInt(id));
                tugas.setNama(rs.getString("nama"));
                tugas.setDeadline(rs.getDate("tgl_deadline"));
                tugas.setLast_mod(rs.getLong("last_mod"));
                tugas.setStatus(rs.getBoolean("status"));
                
                PreparedStatement tempStatement = connection.prepareStatement("SELECT nama FROM categories WHERE id=?");
                tempStatement.setString(1, rs.getString("id_kategori"));
                ResultSet temp = tempStatement.executeQuery();
                temp.next();
                tugas.setKategori(temp.getString("nama"));
                
                PreparedStatement tempStatement1 = connection.prepareStatement("SELECT username FROM assignees WHERE id_tugas=?");
                tempStatement1.setString(1, id);
                ResultSet temp1 = tempStatement1.executeQuery();
                StringBuilder assignee = new StringBuilder();
                while (temp1.next()) {
                    assignee.append(temp1.getString("username"));
                    if (!temp1.isLast())
                        assignee.append(", ");
                }
                tugas.setAssignee(assignee.toString());
                
                PreparedStatement tempStatement2 = connection.prepareStatement("SELECT tag FROM tags WHERE id_tugas=?");
                tempStatement2.setString(1, id);
                ResultSet temp2 = tempStatement2.executeQuery();
                StringBuilder tag = new StringBuilder();
                while (temp2.next()) {
                    tag.append(temp2.getString("tag"));
                    if (!temp2.isLast())
                        tag.append(", ");
                }
                tugas.setTag(tag.toString());
                tasks.add(tugas);
            }
            
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT tugas.* FROM tugas, assignees WHERE tugas.id = assignees.id_tugas AND assignees.username = ?");
            preparedStatement1.setString(1, username);
            ResultSet rs1 = preparedStatement1.executeQuery();
            
            while (rs1.next()) {
                Tugas tugas = new Tugas();
                String id = rs1.getString("id");
                
                tugas.setId(Integer.parseInt(id));
                tugas.setNama(rs1.getString("nama"));
                tugas.setDeadline(rs1.getDate("tgl_deadline"));
                tugas.setLast_mod(rs1.getLong("last_mod"));
                tugas.setStatus(rs1.getBoolean("status"));
                
                PreparedStatement tempStatement = connection.prepareStatement("SELECT nama FROM categories WHERE id=?");
                tempStatement.setString(1, rs1.getString("id_kategori"));
                ResultSet temp = tempStatement.executeQuery();
                temp.next();
                tugas.setKategori(temp.getString("nama"));
                
                PreparedStatement tempStatement1 = connection.prepareStatement("SELECT username FROM assignees WHERE id_tugas=?");
                tempStatement1.setString(1, id);
                ResultSet temp1 = tempStatement1.executeQuery();
                StringBuilder assignee = new StringBuilder();
                assignee.append(rs1.getString("pemilik")).append(", ");
                while (temp1.next()) {
                    assignee.append(temp1.getString("username"));
                    if (!temp1.isLast())
                        assignee.append(", ");
                }
                tugas.setAssignee(assignee.toString());
                
                PreparedStatement tempStatement2 = connection.prepareStatement("SELECT tag FROM tags WHERE id_tugas=?");
                tempStatement2.setString(1, id);
                ResultSet temp2 = tempStatement2.executeQuery();
                StringBuilder tag = new StringBuilder();
                while (temp2.next()) {
                    tag.append(temp2.getString("tag"));
                    if (!temp2.isLast())
                        tag.append(", ");
                }
                tugas.setTag(tag.toString());
                tasks.add(tugas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    
    public void Update(int id, boolean status, long time) {
        try {
            PreparedStatement tempStatement = connection.prepareStatement("SELECT last_mod FROM tugas WHERE id=?");
            tempStatement.setInt(1, id);
            ResultSet temp = tempStatement.executeQuery();
            temp.next();
            
            if (temp.getLong("last_mod") < time) {
                int stat = 0;
                if (status) stat = 1;
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tugas SET STATUS = ?, last_mod = ? WHERE id =?");
                // Parameters start with 1
                preparedStatement.setInt(1, stat);
                preparedStatement.setLong(2, time);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
