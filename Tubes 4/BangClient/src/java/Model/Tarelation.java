/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dell
 */
public class Tarelation {

    private String id_ta;
    private String id_task;
    private String id_attachment;

    public Tarelation(String id_ta, String id_task, String id_attachment) {
        this.id_ta = id_ta;
        this.id_task = id_task;
        this.id_attachment = id_attachment;
    }

    public String getId_ta() {
        return id_ta;
    }

    public void setId_ta(String id_ta) {
        this.id_ta = id_ta;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getId_attachment() {
        return id_attachment;
    }

    public void setId_attachment(String id_attachment) {
        this.id_attachment = id_attachment;
    }
}
