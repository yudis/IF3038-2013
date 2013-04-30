
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Category;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CategoryDao extends DataAccessObject {
    
    public Category getCategory(int idKategori){
    // GET
    // rest/category/[idKategori]
        Category category=null;
        try {
            HttpURLConnection htc = getConnection("rest/category/" + idKategori);
            htc.setRequestMethod("GET");

            Category tmp = new Category();
            tmp.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));

            category = tmp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return category;
    }
    
    public ArrayList<Category> getAllCategory(){
    // GET
    // rest/category/
        ArrayList<Category> result = null;
        try {
            HttpURLConnection htc = getConnection("rest/category/");
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                Category category = new Category();
                category.fromJsonObject(ja.getJSONObject(i));
                result.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int NewKategori(String nama,String pembuat){
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
    // DELETE
    // rest/category/1
        try {
            HttpURLConnection htc = getConnection("rest/category/" + id);
            htc.setRequestMethod("POST");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
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
    // GET
    // rest/category/assignee/wilson
        ArrayList<Category> result = null;
        try {
            HttpURLConnection htc = getConnection("rest/category/assignee/"+URLEncoder.encode(username, "UTF-8"));
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                Category category = new Category();
                category.fromJsonObject(ja.getJSONObject(i));
                result.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String> getUser(int idKategori){
    // GET
    // rest/category/user/1
        ArrayList<String> result = null;
        try {
            HttpURLConnection htc = getConnection("rest/category/user/" + idKategori);
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                result.add(ja.getString(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }    
    
    public Collection<Category> getCategorySearch(String name, int start, int n){
    // GET
    // rest/category/e/0/5
        Category category = null;
        ArrayList<Category> result= new ArrayList<Category>();
        try {
            HttpURLConnection htc = getConnection("/rest/category/"+URLEncoder.encode(name, "UTF-8")+"/"+start+"/"+n);
            htc.setRequestMethod("GET");

            JSONArray ja = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0; i < ja.length(); i++) {
                category = new Category();
                category.fromJsonObject(ja.getJSONObject(i));
                result.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }    
}
