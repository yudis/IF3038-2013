package bean;

public class categoryBean {
    int category_id;
    String category_name;
    String category_creator;
    
    //Constructor
    public categoryBean() {}
    public categoryBean(int category_id, String category_name, String category_creator) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_creator = category_creator;
    }
    
    //Getter
    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_creator() {
        return category_creator;
    }
    
    //Setter
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_creator(String category_creator) {
        this.category_creator = category_creator;
    }
}
