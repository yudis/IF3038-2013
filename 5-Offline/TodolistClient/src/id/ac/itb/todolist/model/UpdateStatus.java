/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Edward Samuel
 */
public class UpdateStatus {
    
    private int idTugas;
    private boolean status;
    private Timestamp timestamp;

    public UpdateStatus(int idTugas, boolean status) {
        this.idTugas = idTugas;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public UpdateStatus(int idTugas, boolean status, Timestamp timestamp) {
        this.idTugas = idTugas;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getIdTugas() {
        return idTugas;
    }

    public void setIdTugas(int idTugas) {
        this.idTugas = idTugas;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UpdateStatus{" + "idTugas=" + idTugas + ", status=" + status + ", timestamp=" + timestamp + '}';
    }
    
    public void writeOut(DataOutputStream out) throws IOException {
        out.writeInt(idTugas);
        out.writeBoolean(status);
        out.writeLong(timestamp.getTime());
    }
    
    public void readIn(DataInputStream in) throws IOException {
        idTugas = in.readInt();
        status = in.readBoolean();
        timestamp = new Timestamp(in.readLong());
    }
}
