
package bean;

public class categoryAsigneeBean {
    int category_id;
    String username;

    public categoryAsigneeBean() {
    }

    public categoryAsigneeBean(int category_id, String username) {
        this.category_id = category_id;
        this.username = username;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getUsername() {
        return username;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
