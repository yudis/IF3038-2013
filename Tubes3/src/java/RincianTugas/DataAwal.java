/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RincianTugas;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.StringTokenizer;

/**
 *
 * @author Christianto
 */

public class DataAwal extends HttpServlet{
    private Connection conn;
    private Statement query;
    
    private int status;
    private String deadline;
    private String tag,sem;
    private String creator;
    private String[] attachment;    
    private String[] assignee;
    
    public DataAwal(int n) {
        try {
            conn = DriverManager.getConnection("jdbc:3306:progin_405_13510003", "root", "");
            query = conn.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM tugas WHERE id_tugas="+n);
            while (result.next()) {
                status = result.getInt("status");
                deadline = result.getString("deadline");
                tag = result.getString("tag");
                sem = result.getString("attachment");
                creator = result.getString("creator");
            }
            result.close();
            
            StringTokenizer pecah = new StringTokenizer(sem,";");
            attachment = new String[pecah.countTokens()];
            for (int i=0;pecah.hasMoreTokens();++i) {
                attachment[i] = pecah.nextToken();
            }
            
            result = query.executeQuery("SELECT * FROM mengerjakan WHERE id_tugas="+n);
            result.last();
            assignee = new String[result.getRow()];
            result.beforeFirst();
            for (int i=0;result.next();++i) {
                assignee[i] = result.getString("username");
            }
            result.close();
        } catch(Exception e) {

        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String[] attachment) {
        this.attachment = attachment;
    }

    public String[] getAssignee() {
        return assignee;
    }

    public void setAssignee(String[] assignee) {
        this.assignee = assignee;
    }
}
