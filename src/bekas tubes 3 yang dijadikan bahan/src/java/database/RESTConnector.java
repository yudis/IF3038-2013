/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author Arief
 */
public class RESTConnector {

    private static String ServerURL;
    protected static Connection connection = null;

    public RESTConnector() {
        ServerURL = "http://localhost:8080/Todolist-WebService/";
    }

    public RESTConnector(String URL) {
        ServerURL = URL;
    }

    public URL getLink(String path) throws MalformedURLException {
        return new URL(this.ServerURL + path);
    }

    public HttpURLConnection getConnection(String path) throws MalformedURLException, IOException {
        return (HttpURLConnection) getLink(path).openConnection();
    }

    public void run() {
        try {
            HttpURLConnection htc = getConnection("query/alalalala");
            htc.setRequestMethod("GET");
            //JSONObject jo = new JSONObject(new JSONTokener(htc.getInputStream()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(RESTConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RESTConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        RESTConnector rs = new RESTConnector();
        rs.run();
    }
}
