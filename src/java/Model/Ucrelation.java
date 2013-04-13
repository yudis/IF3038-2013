/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author dell
 */
public class Ucrelation {

    private String id_uc;
    private String username;
    private String id_category;

    public Ucrelation(String id_uc, String username, String id_category) {
        this.id_uc = id_uc;
        this.username = username;
        this.id_category = id_category;
    }

    public String getId_uc() {
        return id_uc;
    }

    public void setId_uc(String id_uc) {
        this.id_uc = id_uc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }
}
