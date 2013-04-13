package DBOperation;

import Model.Category;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class CategoryOp {

    private Connection connection;

    public CategoryOp() {
        connection = DBUtil.getConnection();
    }

    public void InsertNewCategory(Category category) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO category (name, categ_creator) VALUES (?, ?)");
            ps.setString(1, category.getName());
            ps.setString(2, category.getCateg_creator());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public String FetchIdByName(String categName) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_category FROM category WHERE name=?");
            ps.setString(1, categName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public ArrayList<Category> FetchCategoryForSearch(String parameter) {
        ArrayList<Category> result = new ArrayList<Category>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_category, name FROM category WHERE name LIKE %?%");
            ps.setString(1, parameter);
            ResultSet rs = ps.executeQuery();

            Category temp;
            while (rs.next()) {
                temp = new Category(rs.getString(1), rs.getString(2), null);
                result.add(temp);
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    public void DeleteCategoryById(String categID) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM category WHERE id_category=?");
            ps.setString(1, categID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
