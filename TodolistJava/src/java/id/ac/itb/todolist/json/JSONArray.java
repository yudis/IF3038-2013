package id.ac.itb.todolist.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class JSONArray {

    private final ArrayList data;

    public JSONArray() {
        this.data = new ArrayList();
    }

    public JSONArray(Collection collection) {
        this.data = new ArrayList();
        if (collection != null) {
            Iterator iter = collection.iterator();
            while (iter.hasNext()) {
                this.data.add(JSONObject.wrap(iter.next()));
            }
        }
    }

    public JSONArray put(boolean value) {
        this.put(value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(double value) {
        Double d = new Double(value);
        this.put(d);
        return this;
    }

    public JSONArray put(int value) {
        this.put(new Integer(value));
        return this;
    }

    public JSONArray put(long value) {
        this.put(new Long(value));
        return this;
    }

    public JSONArray put(Object value) {
        this.data.add(value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        boolean commanate = false;
        sb.append('[');

        for (Iterator iter = data.iterator(); iter.hasNext();) {
            Object object = iter.next();
            if (commanate) {
                sb.append(',');
            }
            sb.append(JSONObject.writeValue(object));
            commanate = true;
        }
        sb.append(']');
        return sb.toString();
    }
    
    public int size(){
        return data.size();
    }
}
