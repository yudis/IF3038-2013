/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import javax.servlet.http.Part;

/**
 *
 * @author Agah
 */
public class FileManager {
   public static String getFilename(Part part) {
      for (String cd : part.getHeader("content-disposition").split(";")) {
         if (cd.trim().startsWith("filename")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
         }
      }
      return null;
   }
   
   public static String getExtension (String filename){
      return filename.substring(filename.lastIndexOf("."));
   }
}
