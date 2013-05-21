package id.ac.itb.todolist.model;

import java.sql.Timestamp;

public class Category {

    private int id;
    private String nama;
    private java.sql.Timestamp lastMod;

    public Category() {
    }

    public Category(int id, String nama, Timestamp lastMod) {
        this.id = id;
        this.nama = nama;
        this.lastMod = lastMod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Timestamp getLastMod() {
        return lastMod;
    }

    public void setLastMod(Timestamp lastMod) {
        this.lastMod = lastMod;
    }
}
