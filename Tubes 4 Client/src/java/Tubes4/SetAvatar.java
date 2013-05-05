/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tubes4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class SetAvatar {
    private String charset = "UTF-8";
    
    public Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    
    public String getAvatar(String user) {
        String avatar = null;
        BufferedReader reader = null;
        try {
            URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/SetAvatar").openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            String query = String.format("username=%s", URLEncoder.encode(user, charset));
            OutputStream output = connection.getOutputStream();
            output.write(query.getBytes(charset));
            InputStream outresponse = connection.getInputStream();
            HttpURLConnection httpConnection = (HttpURLConnection)connection;
            int status = httpConnection.getResponseCode();
            if(status == 200) {
                reader = new BufferedReader(new InputStreamReader(outresponse));
                String res = "";
                for (String line; (line = reader.readLine()) != null;)
                    res += line;
                Document doc = loadXMLFromString(res);
                avatar = doc.getElementsByTagName("avatar").item(0).getFirstChild().getNodeValue();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
            return (avatar);
        }
    }
}
