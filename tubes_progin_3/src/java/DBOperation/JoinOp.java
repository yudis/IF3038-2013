/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.*;
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
public class JoinOp {

    private Connection connection;

    public JoinOp() {
        connection = DBUtil.getConnection();
    }

    public ArrayList<Task> GetTasksByUsernameAndCategoryId(String username, String categId) {
        System.out.println("username = " + username);
        System.out.println("categId = " + categId);
        ArrayList<Task> result = new ArrayList<Task>();
        try {
            PreparedStatement ps;
            if (categId.equals("0")) {
                ps = connection.prepareStatement(
                        "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username=? AND utrelation.id_task = task.id_task ORDER BY taskID ASC");
                ps.setString(1, username);
            } else {
                System.out.println("CategId is not 0.");
                ps = connection.prepareStatement(
                        "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username=? AND utrelation.id_task = task.id_task AND id_category=? ORDER BY taskID ASC");
                ps.setString(1, username);
                ps.setString(2, categId);
            }

            ResultSet rs = ps.executeQuery();

            Task temp;
            while (rs.next()) {
                temp = new Task(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), null, null);
                result.add(temp);
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

//    public Task GetTaskInfoByUsernameAndTaskId(String username, String taskId) {
//        try {
//            PreparedStatement ps = connection.prepareStatement(
//                    "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username=? AND utrelation.id_task = task.id_task AND id_task=?");
//            ps.setString(1, username);
//            ps.setString(2, taskId);
//            ResultSet rs = ps.executeQuery();
//
//            Task temp;
//            if (rs.next()) {
//                temp = new Task(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString("id_category"), rs.getString("creator"));
//                return temp;
//            }
//        } catch (SQLException e) {
//            e.getMessage();
//        }
//
//        return null;
//    }
    public ArrayList<String> GetAttachmentPathsByTaskId(String taskId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT path FROM tarelation JOIN attachment WHERE id_task=? AND tarelation.id_attachment = attachment.id_attachment");
            ps.setString(1, taskId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString(1));
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public ArrayList<String> GetTagNamesByTaskId(String taskId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT tag.name AS tagName FROM tag JOIN ttrelation WHERE tag.id_tag = ttrelation.id_tag AND ttrelation.id_task=?");
            ps.setString(1, taskId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString(1));
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public ArrayList<String> GetTagsIdByTaskId(String taskID) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT tag.id_tag AS tagID FROM tag JOIN ttrelation WHERE id_task=? AND tag.id_tag = ttrelation.id_tag");
            ps.setString(1, taskID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("tagID"));
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    /*
     * ==============================
     * JOIN operations in profile.jsp
     * ------------------------------
     * 1. ambil daftar todo list, input suatu username
     * 2. ambil daftar done list, input suatu username
     * 3. ambil category, input id_task
     * ==============================
     */
    //Timo, 10 April 2013
    public ArrayList<Task> GetTodoTasksByUsername(String username) {
        ArrayList<Task> result = new ArrayList<Task>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM utrelation JOIN task WHERE utrelation.id_task=task.id_task AND utrelation.username=? AND task.status='F'");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            Task temp;
            while (rs.next()) {
                temp = new Task(rs.getString("id_task"), rs.getString("name"), rs.getString("deadline"), rs.getString("status"), rs.getString("id_category"), rs.getString("creator"));
                result.add(temp);
            }
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
    //Timo, 10 April 2013

    public ArrayList<Task> GetDoneTasksByUsername(String username) {
        ArrayList<Task> result = new ArrayList<Task>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM utrelation JOIN task WHERE utrelation.id_task=task.id_task AND utrelation.username=? AND task.status='T'");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            Task temp;
            while (rs.next()) {
                temp = new Task(rs.getString("id_task"), rs.getString("name"), rs.getString("deadline"), rs.getString("status"), rs.getString("id_category"), rs.getString("creator"));
                result.add(temp);
            }
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
    //Timo, 10 April 2013

    public Category GetCategoryById_task(String id_task) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT category FROM category JOIN utrelation WHERE category.id_category=utrelation.id_category  AND id_task=?");
            ps.setString(1, id_task);
            ResultSet rs = ps.executeQuery();

            Category temp;
            if (rs.next()) {
                temp = new Category(rs.getString("id_category"), rs.getString("name"), rs.getString("categ_creator"));
                rs.close();
                return temp;
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    public ArrayList<Category> GetCategByUsername(String username) {
        ArrayList<Category> result = new ArrayList<Category>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT name, category.id_category AS categoryID, category.categ_creator FROM category JOIN ucrelation WHERE category.id_category = ucrelation.id_category AND username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            Category temp;
            while (rs.next()) {
                temp = new Category(rs.getString(2), rs.getString(1), rs.getString(3));
                result.add(temp);
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }
    
    public int CheckTtrelationByTagId(String tagId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_tt FROM ttrelation WHERE id_tag=?");
            ps.setString(1, tagId);
            ResultSet rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.close();
                return 1;
            } else {
                rs.close();
                return 0;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return 999;
    }

    //Timo, 12 April 2013
    public ArrayList<Attachment> GetAttachmentFromId_task(String id_task) {
        ArrayList<Attachment> result = new ArrayList<Attachment>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM tarelation JOIN task JOIN attachment WHERE tarelation.id_task=task.id_task AND tarelation.id_attachment=attachment.id_attachment AND task.id_task=?");
            ps.setString(1, id_task);
            ResultSet rs = ps.executeQuery();

            Attachment temp;
            while (rs.next()) {
                temp = new Attachment(rs.getString("id_attachment"), rs.getString("path"));
                result.add(temp);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
    //Timo, 12 April 2013

    public ArrayList<Tag> GetTagFromId_task(String id_task) {
        ArrayList<Tag> result = new ArrayList<Tag>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT tag.id_tag, tag.name FROM ttrelation JOIN task JOIN tag WHERE ttrelation.id_task=task.id_task AND ttrelation.id_tag=tag.id_tag AND task.id_task=?");
            ps.setString(1, id_task);
            ResultSet rs = ps.executeQuery();

            Tag temp;
            while (rs.next()) {
                temp = new Tag(rs.getString("id_tag"), rs.getString("name"));
                result.add(temp);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
}
