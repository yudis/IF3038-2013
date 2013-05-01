/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.dao;

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

    
    public DataAccessObject() {
        ServerURL="http://progin.ap01.aws.af.cm/";
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
