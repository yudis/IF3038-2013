package id.ac.itb.todolist.model;

import java.util.Collection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONModel;
import org.json.JSONObject;

public class Tugas extends JSONModel {
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
    public JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();
        
        jObject.put("id", id);
        jObject.put("nama", nama);
        jObject.put("tglDeadline", tglDeadline.toString());
        jObject.put("status", status);
        jObject.put("lastMod", lastMod.toString());
        jObject.put("pemilik", pemilik.toJsonObject());
        jObject.put("kategori", kategori.toJsonObject());
        jObject.put("attachments", new JSONArray(attachments));
        jObject.put("assignees", new JSONArray(assignees));
        jObject.put("tags", new JSONArray(tags));
        
        return jObject;
    }
    @Override
    public void fromJsonObject(JSONObject jObject) {
        this.id = jObject.getInt("id");
        this.nama = jObject.getString("nama");
        this.tglDeadline = Date.valueOf(jObject.getString("tglDeadline"));
        this.status = jObject.getBoolean("status");
        this.lastMod = Timestamp.valueOf(jObject.getString("lastMod"));
        JSONObject userJson = jObject.getJSONObject("pemilik");
        if(userJson != null){
            User user = new User();
            user.fromJsonObject(userJson);
            this.pemilik = user;
        }
        JSONObject kategoriJson = jObject.getJSONObject("kategori");
        if(kategoriJson != null){
            Category kategori = new Category();
            kategori.fromJsonObject(kategoriJson);
            this.kategori = kategori;
        }
         JSONArray jsonAttachments = jObject.getJSONArray("attachments");
        if (jsonAttachments != null) {
            if (jsonAttachments.length() > 0) {
                this.attachments = new ArrayList<Attachment>();
            }

            for (int i = 0, len = jsonAttachments.length(); i < len; i++) {
                JSONObject jsonAttachment = jsonAttachments.getJSONObject(i);
                Attachment attachment = new Attachment();
                attachment.fromJsonObject(jsonAttachment);
                this.attachments.add(attachment);
            }
        }
        JSONArray jsonAssignees = jObject.getJSONArray("assignees");
        if (jsonAssignees != null) {
            if (jsonAssignees.length() > 0) {
                this.assignees = new ArrayList<User>();
            }

            for (int i = 0, len = jsonAssignees.length(); i < len; i++) {
                JSONObject jsonAssignee = jsonAssignees.getJSONObject(i);
                User assignee = new User();
                assignee.fromJsonObject(jsonAssignee);
                this.assignees.add(assignee);
            }
        }

        JSONArray jsonTags = jObject.getJSONArray("tags");
        if (jsonTags != null) {
            if (jsonTags.length() > 0) {
                this.tags = new ArrayList<String>();
            }

            for (int i = 0, len = jsonTags.length(); i < len; i++) {
                this.tags.add(jsonTags.getString(i));
            }
        }
    }
}
