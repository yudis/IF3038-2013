package id.ac.itb.todolist.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import org.json.JSONModel;
import org.json.JSONObject;

public class Category extends JSONModel implements Serializable {

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
    
    public void writeOut(DataOutputStream out) throws IOException {
        out.writeInt(id);
        out.writeUTF(nama);
        out.writeLong(lastMod.getTime());
    }
    
    public void readIn(DataInputStream in) throws IOException {
        id = in.readInt();
        nama = in.readUTF();
        lastMod = new Timestamp(in.readLong());
    }
    
    @Override
    public JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();

        jObject.put("id", id);
        jObject.put("nama", nama);
        jObject.put("lastMod", lastMod);

        return jObject;
    }

    @Override
    public void fromJsonObject(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.nama = jObject.optString("nama");
        this.lastMod = java.sql.Timestamp.valueOf(jObject.optString("lastMod"));
    }
}
