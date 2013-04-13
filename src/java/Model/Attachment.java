/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dell
 */
public class Attachment {

    private String id_attachment;
    private String path;

    public Attachment(String id_attachment, String path) {
        this.id_attachment = id_attachment;
        this.path = path;
    }

    public String getId_attachment() {
        return id_attachment;
    }

    public void setId_attachment(String id_attachment) {
        this.id_attachment = id_attachment;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
