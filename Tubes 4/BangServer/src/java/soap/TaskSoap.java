/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import JSON.JSONObject;
import JSON.JSONTokener;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M500-S430
 */
@WebService(serviceName = "TaskSoap")
public class TaskSoap {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        try {
            JSONObject jtask = new JSONObject(new JSONTokener(txt));
            String fileName = jtask.getString("fileName");
            String[] assignees = jtask.getString("newTaskAssignee").split(",");
            String[] tags = jtask.getString("newTaskTags").split(",");
            String name = jtask.getString("newTaskName");
            String deadline = jtask.getString("newDeadline");
            String username = jtask.getString("creator");
            String category = jtask.getString("category");
            String found = jtask.getString("upload");
            String idTask;

            Connection con = DBUtil.getConnection();
            String query;
            PreparedStatement ps;
            ResultSet rs1, rs2;

            query = "INSERT INTO task (name, deadline, status, id_category, creator) VALUES ('"
                    + name + "', '" + deadline + "', 'F', '" + category + "', '" + username + "');";
            ps = con.prepareStatement(query);
            ps.executeUpdate();

            query = "SELECT id_task FROM task WHERE name='" + name + "' AND creator='" + username + "';";
            ps = con.prepareStatement(query);
            rs1 = ps.executeQuery();
            rs1.next();
            idTask = rs1.getString(1);

            query = "INSERT INTO utrelation (username, id_task) VALUES ('" + username + "', '" + rs1.getString(1) + "');";
            ps = con.prepareStatement(query);
            ps.executeUpdate();

            for (int j = 0; j < assignees.length; j++) {
                if (!assignees[j].isEmpty()) {
                    query = "INSERT INTO utrelation (username, id_task) VALUES ('" + assignees[j] + "', '" + idTask + "');";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                }
            }

            for (int j = 0; j < tags.length; j++) {
                System.out.println("haiyaa 1");
                query = "SELECT name FROM tags WHERE name='" + tags[j] + "'";
                ps = con.prepareStatement(query);
                rs2 = ps.executeQuery();
                if (!rs2.next()) {
                    System.out.println("haiyaa insert new tags");
                    query = "INSERT INTO tags (name) VALUES ('" + tags[j] + "');";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                }
                System.out.println("haiyaa 2");
                query = "SELECT id_tags FROM tags WHERE name='" + tags[j] + "';";
                ps = con.prepareStatement(query);
                rs2 = ps.executeQuery();
                rs2.next();
                System.out.println("haiyaa 3");
                query = "INSERT INTO ttrelation (id_task, id_tags) VALUES ('" + idTask + "', '" + rs2.getString(1) + "');";
                ps = con.prepareStatement(query);
                ps.executeUpdate();
            }
            
            if (found.contentEquals("yse"))
            {
                query = "INSERT INTO attachment (path) VALUES ('" + fileName + "');";
                ps = con.prepareStatement(query);
                ps.executeUpdate();

                query = "SELECT id_attachment FROM attachment WHERE path='" + fileName + "';";
                ps = con.prepareStatement(query);
                rs2 = ps.executeQuery();
                rs2.next();

                query = "INSERT INTO tarelation (id_task, id_attachment) VALUES ('" + idTask + "', '" + rs2.getString(1) + "');";
                ps = con.prepareStatement(query);
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(TaskSoap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "200";
    }
}
