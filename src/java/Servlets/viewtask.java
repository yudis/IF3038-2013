/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ConnectDB.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 *
 * @author PANDU
 */
public class viewtask {
    public String[] task;
	public String[][] attach;
	public String[][] tag;
	public String[][] assignee;
	public String[][] komen;
	public String[][] komentator;
    
    public viewtask (String x) throws IOException, ServletException, SQLException  {
        
        String sql = "";
        String[][] hasil;
        
        // AMBIL tugas
        sql = "SELECT * FROM tugas WHERE idtugas='"+x+"'";
        hasil = ConnectDB.getHasilQuery(sql);
        if (hasil.length!=0) { // jika ga kosong
            task = hasil[0];
        
            // AMBIL attachment
            sql = "SELECT * FROM attachment";
            sql += " WHERE tugas_idtugas='"+x+"'";
            attach = ConnectDB.getHasilQuery(sql);

            // AMBIL assignee
            sql = "SELECT a.* FROM accounts_has_tugas AS b, accounts AS a";
            sql += " WHERE b.tugas_idtugas='"+x+"'";
            sql += " AND b.accounts_idaccounts = a.idaccounts";
            assignee = ConnectDB.getHasilQuery(sql);

            // AMBIL tag
            sql = "SELECT idtag, nama FROM tugas_has_tag AS b, tag AS a";
            sql += " WHERE b.tugas_idtugas='"+x+"'";
            sql += " AND b.tag_idtag = a.idtag";
            tag = ConnectDB.getHasilQuery(sql);

            // AMBIL komen & komentator
            sql = "SELECT * FROM komentar";
            sql += " WHERE tugas_idtugas='"+x+"'";
            sql += " ORDER BY created";
            hasil = ConnectDB.getHasilQuery(sql);
            komen = new String[hasil.length][];
            komentator = new String[hasil.length][];
            int i=0;
            for (String[] temp : hasil) {
                komen[i] = temp;
                // Ambil Komentator
                String sql2 = "SELECT * FROM accounts";
                sql2 += " WHERE idaccounts='"+temp[3]+"'";
                String[][] hasil2 = ConnectDB.getHasilQuery(sql2);
                komentator[i] = hasil2[0];
                i = i+1;
            }
        }
        else 
        { 
            task = new String[5];            
            attach = new String[5][];
            tag = new String[5][];
            assignee = new String[5][];
            komen = new String[5][];
            komentator = new String[5][];
            // isi dengan null
            for (int i=0; i<5; i++) {
                task[i] = "";
                attach[i] = new String[9];
                tag[i] = new String[9];
                assignee[i] = new String[9];
                komen[i] = new String[9];
                komentator[i] = new String[9];
            }
            for (int i=0; i<9; i++) {
                for (int j=0; j<5; j++) {
                    attach[j][i] = "";
                    tag[j][i] = "";
                    assignee[j][i] = "";
                    komen[j][i] = "";
                    komentator[j][i] = "";
                }
            }
        }
        
    }
    public String[]  getTask() { return task; }
	public String[][]  getAttachment() { return attach; }
	public String[][]  getAssignee() { return assignee; }
	public String[][]  getTag() { return tag; }
	public String[][]  getKomen() { return komen; }
	public String[][]  getKomentator() { return komentator; }
    
}
