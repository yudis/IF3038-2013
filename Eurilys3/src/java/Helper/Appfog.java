/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Agah
 */
public class Appfog {
   
   public static String getBaseURL(){
//      return "http://localhost:8084/doMetro-WebService/";
      return "http://dometro.ap01.aws.af.cm/";
   }
   
   public static URLConnection getConnection(String url) throws MalformedURLException, IOException{
      try{
         URLConnection conn = new URL(url).openConnection();
         conn.setDoInput(true);
         conn.setDoOutput(true);
         return conn;
      } catch (Exception e){
         return null;
      }
   }
   
   public static String getString(InputStream in) throws IOException {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      
      int i;
      while ((i = in.read()) != -1) {
         bout.write(i);
      }
      return new String(bout.toByteArray());
   }
   
   public static String getResponse(String url) throws IOException{
      return (getString(getConnection(url).getInputStream()));
   }
}
