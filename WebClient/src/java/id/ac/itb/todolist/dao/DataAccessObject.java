/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.util.DB;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

/**
 *
 * @author Edward Samuel
 */
public class DataAccessObject {
    protected String ServerURL;
    
    protected Connection connection;

    
    public DataAccessObject() {
        ServerURL="http://dolist.ap01.aws.af.cm/";
        connection = DB.getConnection();
    }
    
    public DataAccessObject(String URL) {
        ServerURL=URL;
    }
    
    public URL getLink(String path) throws MalformedURLException{
        return new URL(this.ServerURL+path);
    }
    
    public HttpURLConnection getConnection(String path) throws MalformedURLException, IOException{
        return (HttpURLConnection) getLink(path).openConnection();
    }
}
