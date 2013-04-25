
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryDao extends DataAccessObject {
    
    public Category getCategory(int idKategori) {
        Category category=null;
        try{
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM categories WHERE id=? ;");
            preparedStatement.setInt(1, idKategori);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                category=new Category();
                category.setId(rs.getInt("id"));
                category.setNama(rs.getString("nama"));
                category.setLastMod(rs.getTimestamp("last_mod"));
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    
    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM categories ;");

            ResultSet rs = preparedStatement.executeQuery();
            
            result = new ArrayList<Category>();
            while (rs.next()) {
                Category item = new Category();
                item.setId(rs.getInt("id"));
                item.setNama(rs.getString("nama"));
                item.setLastMod(rs.getTimestamp("last_mod"));
                                
                result.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int NewKategori(String nama, String pembuat) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO categories(id,nama) VALUES (0,?);");
            // Parameters start with 1
            preparedStatement.setString(1, nama);
            preparedStatement.executeUpdate();
            return addNewestCoordinator(pembuat);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int DeleteKategori(int id){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM categories WHERE id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addCoordinator(int id,String pembuat) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO coordinator(id_kategori,user) VALUES (?,?);");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, pembuat);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int addNewestCoordinator(String pembuat) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT id FROM categories ORDER BY last_mod DESC LIMIT 1;");
            ResultSet rs = preparedStatement.executeQuery();
            
            int x=-1;
            if (rs.next()) {
                x= addCoordinator(rs.getInt("id"),pembuat);
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public ArrayList<Category> getAssigneeCat(String username){
        ArrayList<Category> result = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT t.id AS id, t.nama AS nama, tgl_deadline,  `status` , t.last_mod AS last_mod, pemilik, id_kategori,username, c.nama AS nama_kategori FROM categories c, tugas t, assignees s WHERE id_kategori NOT IN (SELECT id_kategori FROM coordinator WHERE user=? ) AND username=? AND t.id=s.id_tugas AND t.id_kategori=c.id");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            result = new ArrayList<Category>();
            while (rs.next()) {
                Category item = new Category();
                item.setId(rs.getInt("id"));
                item.setNama(rs.getString("nama"));
                item.setLastMod(rs.getTimestamp("last_mod"));
                                
                result.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String> getUser(int idKategori){
        ArrayList<String> result = null;
        try{
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT user FROM coordinator WHERE id_kategori=? ;");
            preparedStatement.setInt(1, idKategori);
            ResultSet rs = preparedStatement.executeQuery();

            result = new ArrayList<String>();
            while (rs.next()) {
                String item=rs.getString("user");
                                
                result.add(item);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }    
    
    public Collection<Category> getCategorySearch(String name, int start, int n){
        Category category = null;
        ArrayList<Category> result= new ArrayList<Category>();
        String qry = "SELECT * FROM categories WHERE nama LIKE '%" + name + "%' LIMIT " + start + ", " + n + ";";
        try{
            PreparedStatement preparedStatement = connection.
                    prepareStatement(qry);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setNama(rs.getString("nama"));
                category.setLastMod(rs.getTimestamp("last_mod"));
                result.add(category);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }    
}
