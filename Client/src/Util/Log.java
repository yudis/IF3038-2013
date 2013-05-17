package Util;

import java.sql.Timestamp;

public class Log {
    private Timestamp timestamp;
    private boolean  value;

    public Log (Timestamp timestamp, boolean value){
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean isValue() {
        return value;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
     @Override
     public String toString() {
    StringBuilder result = new StringBuilder();
    String NEW_LINE = System.getProperty("line.separator");

    result.append(this.getClass().getName()).append(" Object {").append(NEW_LINE);
    result.append(" Timestamp: ").append(timestamp.toString()).append(NEW_LINE);
    result.append(" Value: ").append(value).append(NEW_LINE);
    result.append("}");

    return result.toString();
  }
}
