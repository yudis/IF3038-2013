package id.ac.itb.todolist.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SearchAutoCompleteDao extends DataAccessObject {

    public ArrayList<String> getSearchAC(String q, String filter) {
        // GET
        // rest/searchac/username/w
        ArrayList<String> result = new ArrayList<String>();
        
        throw new UnsupportedOperationException("Not supported yet.");

//        HashSet h = new HashSet(result);
//        result.clear();
//        result.addAll(h);
//        Collections.sort(result);
//
//        return result;
    }
}
