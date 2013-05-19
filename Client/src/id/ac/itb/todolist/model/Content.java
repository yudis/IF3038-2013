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
    private int id;
    private String nama;
    private String pemilik;
    private String kategori;
    private String Deadline;
    private String assignees;
    private String tags;
    private boolean status;

    public Content(int id, String nama, String pemilik, String kategori, String Deadline, String assignees, String tags, boolean status) {
        this.id = id;
        this.nama = nama;
        this.pemilik = pemilik;
        this.kategori = kategori;
        this.Deadline = Deadline;
        this.assignees = assignees;
        this.tags = tags;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public boolean getStatus() {
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName()).append(" Object {").append(NEW_LINE);
        result.append(" Nama: ").append(nama).append(NEW_LINE);
        result.append(" Pemilik: ").append(pemilik).append(NEW_LINE);
        result.append(" Kategori: ").append(kategori).append(NEW_LINE);
        result.append(" Deadline: ").append(Deadline).append(NEW_LINE);
        result.append(" Assignees: ").append(assignees).append(NEW_LINE);
        result.append(" Tags: ").append(tags).append(NEW_LINE);
        result.append(" Status: ").append(status).append(NEW_LINE);

        result.append("}");

        return result.toString();
    }
}
