package id.ac.itb.todolist.json;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {

    private HashMap<String, Object> data;

    public JSONObject() {
        this.data = new HashMap<String, Object>();
    }

    public JSONObject put(String key, boolean value) {
        this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject put(String key, double value) {
        this.put(key, new Double(value));
        return this;
    }

    public JSONObject put(String key, int value) {
        this.put(key, new Integer(value));
        return this;
    }

    public JSONObject put(String key, long value) {
        this.put(key, new Long(value));
        return this;
    }

    public JSONObject put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean commanate = false;

        for (Map.Entry<String, Object> item : data.entrySet()) {
            String key = item.getKey();
            Object value = item.getValue();

            if (commanate) {
                sb.append(',');
            }
            
            sb.append(quote(key.toString()));
            sb.append(':');
            
            sb.append(writeValue(value));
            commanate = true;
        }

        sb.append("}");
        return sb.toString();
    }

    static String quote(String string) {
        StringBuilder sb = new StringBuilder();

        if (string == null || string.length() == 0) {
            sb.append("\"\"");
            return sb.toString();
        }

        char b;
        char c = 0;
        String hhhh;
        int i;
        int len = string.length();

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            b = c;
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    if (b == '<') {
                        sb.append('\\');
                    }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ' || (c >= '\u0080' && c < '\u00a0')
                            || (c >= '\u2000' && c < '\u2100')) {
                        sb.append("\\u");
                        hhhh = Integer.toHexString(c);
                        sb.append("0000", 0, 4 - hhhh.length());
                        sb.append(hhhh);
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }
    
    static String writeValue(Object value) {
        StringBuilder sb = new StringBuilder();
        
        if (value == null) {
            sb.append("null");
        } else if (value instanceof JSONObject) {
            sb.append(value);
        } else if (value instanceof JSONArray) {
            sb.append(value);
        } else if (value instanceof Number) {
            sb.append(numberToString((Number) value));
        } else if (value instanceof Boolean) {
            sb.append(value.toString());
        } else {
            sb.append(quote(value.toString()));
        }
        return sb.toString();
    }
    
    static String numberToString(Number number) {        
        String string = number.toString();
        if (string.indexOf('.') > 0 && string.indexOf('e') < 0 &&
                string.indexOf('E') < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }
}
