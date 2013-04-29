package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryDao extends DataAccessObject {

    public Category getCategory(int idKategori) {
        // GET
        // rest/category/1
        Category category = null;
        throw new UnsupportedOperationException("Not supported yet.");
        // return category;
    }

    public ArrayList<Category> getAllCategory() {
        // GET
        // rest/category/
        ArrayList<Category> result = null;
        throw new UnsupportedOperationException("Not supported yet.");
        //return result;
    }

    public int createNewKategori(String nama, String pembuat) {
        
        throw new UnsupportedOperationException("Not supported yet.");
        //return -1;
    }

    public int deleteKategori(int id) {
        // DELETE
        // rest/category/[1]
        throw new UnsupportedOperationException("Not supported yet.");
        //return -1;
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
        
        throw new UnsupportedOperationException("Not supported yet.");
        //return result;
    }

    public ArrayList<String> getUser(int idKategori) {
        // GET
        // rest/category/user/1
        ArrayList<String> result = null;
        throw new UnsupportedOperationException("Not supported yet.");
        //return result;
    }

    public Collection<Category> getCategorySearch(String name, int start, int n) {
        // GET
        // rest/category/e/0/5
        Category category = null;
        ArrayList<Category> result = new ArrayList<Category>();
        throw new UnsupportedOperationException("Not supported yet.");
        //return result;
    }
}
