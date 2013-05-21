/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocol;

/**
 *
 * @author Arief
 */
public class ArsProtocol {
    private static final int C_READY = 0;
    private static final int C_LOGINENTER = 1;
    private static final int C_LOGINSUCCESS = 2;
    
    private int state;
    
    public ArsProtocol(){
        state = C_READY;
    }
    
    public String processMsg(char[] theInput){
        String theOutput = null;
        switch(getState()){
            case C_READY:{
                if(ArsMsgType.getCode(theInput)==ArsMsgType.LOGIN_CODE) {
                    state = C_LOGINENTER;
                    theOutput = new String(theInput);
                }
                else {
                    theOutput = new String(ArsMsgType.MsgFailed());
                }
                break;
            }
            case C_LOGINENTER:{
                if(ArsMsgType.getCode(theInput)==ArsMsgType.SUCCESS_CODE) {
                    theOutput = new String(theInput);
                    state = C_LOGINSUCCESS;
                }
                else {
                    theOutput = new String(ArsMsgType.MsgFailed());
                    state = C_READY;
                }
                break;
            }
            case C_LOGINSUCCESS:{
                if(ArsMsgType.getCode(theInput)==ArsMsgType.LOGOUT_CODE) {
                    state = C_READY;
                }
                theOutput = new String(theInput);
                break;
            }
        }
        return theOutput;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }
    
}
