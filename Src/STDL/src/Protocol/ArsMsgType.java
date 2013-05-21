/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arief
 */
public class ArsMsgType {
    
    public static String PSTR = "SharedToDoList";
    public static String Separator = "#";
    
    public static char LOGIN_CODE = 220;        //'Ü'
    public static char LIST_CODE = 221;         //'Ý'
    public static char DETAIL_CODE = 222;       //'Þ'
    public static char CHANGESTATUS_CODE = 223; //'ß'
    public static char LOGOUT_CODE = 224;         //'à'
    
    public static char SUCCESS_CODE = 214;      //'Ö'
    public static char FAILED_CODE = 215;       //'×'
    
    public static char getCode(char[] msg){
        return msg[14];
    }
    
    public static char[] MsgSuccess(){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(SUCCESS_CODE);
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgFailed(){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(FAILED_CODE);
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgLogin(String userId, String pass){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(LOGIN_CODE);
        msg.append(Separator);
        msg.append(userId);
        msg.append(Separator);
        msg.append(pass);
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgList(){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(LIST_CODE);
        return msg.toString().toCharArray();
    }
    
    public static char[] ListAnswer(ResultSet RSet){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(LIST_CODE);
        try {
            while(RSet.next()) {
                msg.append(Separator);
                msg.append(RSet.getString("namaTask"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArsMsgType.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgDetail(String namaTask){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(DETAIL_CODE);
        msg.append(Separator);
        msg.append(namaTask);
        return msg.toString().toCharArray();
    }
    
    public static char[] DetailAnswer(ResultSet RSet){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(DETAIL_CODE);
        try {
            while(RSet.next()){
                msg.append(Separator);
                msg.append(RSet.getString("namaTask"));
                msg.append(Separator);
                msg.append(RSet.getDate("deadline").toString());
                msg.append(Separator);
                msg.append(RSet.getString("status"));
                msg.append(Separator);
                msg.append(RSet.getString("creatorTaskName"));
                msg.append(Separator);
                msg.append(RSet.getString("namaKategori"));
                msg.append(Separator);
                msg.append(RSet.getTimestamp("timestamp").toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArsMsgType.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgChangeStatus(String namaTask, String status, Timestamp TS){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(CHANGESTATUS_CODE);
        msg.append(Separator);
        msg.append(namaTask);
        msg.append(Separator);
        msg.append(status);
        msg.append(Separator);
        msg.append(TS.toString());
        return msg.toString().toCharArray();
    }
    
    public static char[] MsgLogout(){
        StringBuilder msg = new StringBuilder();
        msg.append(PSTR);
        msg.append(LOGOUT_CODE);
        return msg.toString().toCharArray();
    }
    
}
