/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class MessageProtokol {

    public static String ListRequest(String ClientID) {
        String s;
        s = "list_"+ ClientID;
        return s;
    }
    public static String PushRequest(String ClientID,String TaskID,String status, String Time) {
        String msg;
        msg = "push_"+ClientID+"_"+TaskID+"_"+status+"_"+Time;
        return msg;
    }
   
    
}
