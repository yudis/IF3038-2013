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
    
    private String nama;
    private String kategori;
    private int status;
    private int id_kategori;
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
                nama = result.getString("nama");
                status = result.getInt("status");
                deadline = result.getString("deadline");
                tag = result.getString("tag");
                sem = result.getString("attachment");
                creator = result.getString("creator");
                id_kategori = result.getInt("id_kategori");
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
            
            result = query.executeQuery("SELECT * FROM kategori where id_kategori="+id_kategori);
            result.first();
            kategori = result.getString("nama_kategori");
            result.close();
        } catch(Exception e) {

        }
    }

    public String getNama() {
        return nama;
    }
    
    public int getStatus() {
        return status;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getTag() {
        return tag;
    }

    public String getCreator() {
        return creator;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public String[] getAssignee() {
        return assignee;
    }
    
    public String getKategori() {
        return kategori;
    }
}
