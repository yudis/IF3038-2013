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

    public Category getCategory(int idKategori) {
        // GET
        // rest/category/1

        Category category = null;

        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/" + idKategori);
            htc.setRequestMethod("GET");

            Category tmp = new Category();
            tmp.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));

            category = tmp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return category;
    }

    public ArrayList<Category> getAllCategory() {
        // GET
        // rest/category/
        ArrayList<Category> result = null;
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/");
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Category category = new Category();
                category.fromJsonObject(jArray.getJSONObject(i));
                result.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int createNewKategori(String nama, String pembuat) {

        throw new UnsupportedOperationException("Not supported yet.");
        //return -1;
    }

    public int deleteKategori(int id) {
        // DELETE
        // rest/category/[1]
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/" + id);
            htc.setRequestMethod("DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int addCoordinator(int id, String pembuat) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return -1;
    }

    public int addNewestCoordinator(String pembuat) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return -1;
    }

    public ArrayList<Category> getAssigneeCat(String username) {
        // GET
        // rest/category/assignee/wilson
        ArrayList<Category> result = null;

       try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/assignee/"+URLEncoder.encode(username, "UTF-8"));
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Category category = new Category();
                category.fromJsonObject(jArray.getJSONObject(i));
                result.add(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getUser(int idKategori) {
        // GET
        // rest/category/user/1
        ArrayList<String> result = null;
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/user/" + idKategori);
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

    public Collection<Category> getCategorySearch(String name, int start, int n) {
        // GET
        // rest/category/e/0/5
        
        ArrayList<Category> result = new ArrayList<Category>();
         
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/category/"+URLEncoder.encode(name, "UTF-8")+"/"+start+"/"+n);
            htc.setRequestMethod("GET");

            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Category temp = new Category();
                temp.fromJsonObject(jArray.getJSONObject(i));
                result.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
