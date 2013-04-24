/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Felix
 */
public class SearchAutoCompleteDao extends DataAccessObject {
        
    public ArrayList<String> getSearchAC(String q, String filter){
        ArrayList<String> result = new ArrayList<String>();    
        try {
            int n = 0;
            
            if ((("All".equals(filter)) || ("Username".equals(filter))) && (n < 4)){
                String qry1 = "SELECT username FROM users WHERE username LIKE '" + q + "%';";            
                PreparedStatement preparedStatement1 = connection.
                        prepareStatement(qry1);

                ResultSet rs1 = preparedStatement1.executeQuery();

                while ((rs1.next()) && (n < 4)) {
                    result.add(rs1.getString("username"));
                    n++;
                }
            }
            
            if ((("All".equals(filter)) || ("Title".equals(filter))) && (n < 4)){
                String qry2 = "SELECT nama FROM categories WHERE nama LIKE '" + q + "%';";            
                PreparedStatement preparedStatement2 = connection.
                        prepareStatement(qry2);

                ResultSet rs2 = preparedStatement2.executeQuery();

                while ((rs2.next()) && (n < 4)) {
                    result.add(rs2.getString("nama"));
                    n++;
                }
            }
            
            if ((("All".equals(filter)) || ("Task".equals(filter))) && (n < 4)){
                String qry3 = "SELECT nama FROM tugas WHERE nama LIKE '" + q + "%';";            
                PreparedStatement preparedStatement3 = connection.
                        prepareStatement(qry3);

                ResultSet rs3 = preparedStatement3.executeQuery();

                while ((rs3.next()) && (n < 4)) {
                    result.add(rs3.getString("nama"));
                    n++;
                }
                
                String qry4 = "SELECT tag FROM tags WHERE tag LIKE '" + q + "%';";            
                PreparedStatement preparedStatement4 = connection.
                        prepareStatement(qry4);

                ResultSet rs4 = preparedStatement4.executeQuery();

                while ((rs4.next()) && (n < 4)) {
                    result.add(rs4.getString("tag"));
                    n++;
                }                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        HashSet h = new HashSet(result);
        result.clear();
        result.addAll(h);
        Collections.sort(result);
        
        return result;
    }
    
}
