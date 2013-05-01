package Helper;


import java.util.Random;

public class HashGenerator {
   public static String getHash(){
      String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      String hash = "";
      Random random = new Random();
      int length = chars.length();
      for (int i = 0; i < 8; i++){
         hash += chars.charAt(random.nextInt(length-1));
      }
      
      return hash;
   }
}
