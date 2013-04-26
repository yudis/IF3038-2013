/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.util.DB;
import java.sql.Connection;

/**
 *
 * @author Edward Samuel
 */
public class DataAccessObject {
    protected Connection connection;

    public DataAccessObject() {
        connection = DB.getConnection();
    }
}
