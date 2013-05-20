package id.ac.itb.todolist.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import org.json.JSONModel;
import org.json.JSONObject;

public class Attachment extends JSONModel {

    private int idAttachment;
    private int idTugas;
    private String name;
    private String filename;
    private String type;

    public Attachment() {
    }

    public Attachment(int idAttachment, int idTugas, String name, String filename, String type) {
        this.idAttachment = idAttachment;
        this.idTugas = idTugas;
        this.name = name;
        this.filename = filename;
        this.type = type;
    }

    public int getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(int idAttachment) {
        this.idAttachment = idAttachment;
    }

    public int getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(int idTugas) {
        this.idTugas = idTugas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void writeOut(DataOutputStream out) throws IOException {
        out.writeInt(idAttachment);
        out.writeInt(idTugas);
        out.writeUTF(name);
        out.writeUTF(filename);
        out.writeUTF(type);
    }
    
    public void readIn(DataInputStream in) throws IOException {
        idAttachment = in.readInt();
        idTugas = in.readInt();
        name = in.readUTF();
        filename = in.readUTF();
        type = in.readUTF();
    }
    
    @Override
    public JSONObject toJsonObject() {
        JSONObject jObject = new JSONObject();

        jObject.put("idAttachment", idAttachment);
        jObject.put("idTugas", idTugas);
        jObject.put("name", name);
        jObject.put("filename", filename);
        jObject.put("type", type);

        return jObject;
    }

    @Override
    public void fromJsonObject(JSONObject jObject) {
        this.idAttachment = jObject.optInt("idAttachment");
        this.idTugas = jObject.optInt("idTugas");
        this.name = jObject.optString("name");
        this.filename = jObject.optString("filename");
        this.type = jObject.optString("type");
    }
}
