/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package progin5;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class MessageProtokol {

    public static String ListRequest(String ClientID) {
        String s;
        s = "List_"+ ClientID;
        return s;
    }
    public static String PushRequest(String ClientID,String TaskID, String Time) {
        String msg;
        msg = "Push_"+ClientID+"_"+TaskID+"_"+Time;
        return msg;
    }
   
    
}
