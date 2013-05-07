package id.ac.itb.todolist.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataAccessObject {

    protected String mainUrl;

    public DataAccessObject() {
        this.mainUrl = "http://localhost:8084/Todolist-Offline-WebService";
    }

    public DataAccessObject(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public URL getURL(String path) throws MalformedURLException {
        return new URL(this.mainUrl + path);
    }

    public HttpURLConnection getHttpURLConnection(String path) throws MalformedURLException, IOException {
        return (HttpURLConnection) getURL(path).openConnection();
    }
}
