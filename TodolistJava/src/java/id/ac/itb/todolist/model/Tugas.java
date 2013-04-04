package id.ac.itb.todolist.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import id.ac.itb.todolist.util.BaseModel;
import java.util.Collection;
import java.sql.Date;
import java.sql.Timestamp;

public class Tugas extends BaseModel {
    private int id;
    private String nama;
    private Date tglDeadline;
    private boolean status;
    private Timestamp lastMod;
    private User pemilik;
    private Category kategori;
    private Collection<Attachment> attachments;
    private Collection<User> assignees;
    private Collection<String> tags;
        
    public Tugas() {
    }

    public Tugas(int id, String nama, Date tglDeadline, boolean status, Timestamp lastMod, User pemilik, Category kategori, Collection<Attachment> attachment, Collection<User> assignees, Collection<String> tags) {
        this.id = id;
        this.nama = nama;
        this.tglDeadline = tglDeadline;
        this.status = status;
        this.lastMod = lastMod;
        this.pemilik = pemilik;
        this.kategori = kategori;
        this.attachments = attachment;
        this.assignees = assignees;
        this.tags = tags;
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

    public Date getTglDeadline() {
        return tglDeadline;
    }

    public void setTglDeadline(Date tglDeadline) {
        this.tglDeadline = tglDeadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getLastMod() {
        return lastMod;
    }

    public void setLastMod(Timestamp lastMod) {
        this.lastMod = lastMod;
    }

    public User getPemilik() {
        return pemilik;
    }

    public void setPemilik(User pemilik) {
        this.pemilik = pemilik;
    }

    public Category getKategori() {
        return kategori;
    }

    public void setKategori(Category kategori) {
        this.kategori = kategori;
    }

    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Collection<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(Collection<User> assignees) {
        this.assignees = assignees;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    @Override
    public JsonElement toJsonElement() {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("id", id);
        jObject.addProperty("nama", nama);
        jObject.addProperty("tglDeadline", tglDeadline.toString());
        jObject.addProperty("status", status);
        jObject.addProperty("lastMod", lastMod.toString());
        jObject.add("pemilik", pemilik.toJsonElement());
        jObject.add("kategori", kategori.toJsonElement());
        
        JsonArray jAttachments = new JsonArray();
        for (BaseModel item : attachments) {jAttachments.add(item.toJsonElement());}        
        jObject.add("attachments", jAttachments);
        
        JsonArray jAssignees = new JsonArray();
        for (BaseModel item : assignees) {jAssignees.add(item.toJsonElement());}        
        jObject.add("assignees", jAssignees);
        
        JsonArray jTags = new JsonArray();
        Gson gson = new Gson();
        for (String item : tags) {jTags.add(gson.toJsonTree(item));}        
        jObject.add("tags", jTags);
        
        return jObject;
    }    
}
