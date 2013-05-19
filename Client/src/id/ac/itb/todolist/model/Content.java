/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.model;

/**
 *
 * @author Felix
 */
public class Content {
    private String nama;
    private String pemilik;
    private String kategori;
    private String Deadline;
    private String assignees;
    private String tags;
    private boolean status;

    public Content(String nama, String pemilik, String kategori, String Deadline, String assignees, String tags, boolean status) {
        this.nama = nama;
        this.pemilik = pemilik;
        this.kategori = kategori;
        this.Deadline = Deadline;
        this.assignees = assignees;
        this.tags = tags;
        this.status = status;
    }
    
    public String getNama() {
        return nama;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDeadline() {
        return Deadline;
    }

    public String getAssignees() {
        return assignees;
    }

    public String getTags() {
        return tags;
    }

    public boolean isStatus() {
        return status;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setDeadline(String Deadline) {
        this.Deadline = Deadline;
    }

    public void setAssignees(String assignees) {
        this.assignees = assignees;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
