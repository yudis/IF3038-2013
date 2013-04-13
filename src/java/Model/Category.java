package Model;

/**
 *
 * @author dell
 */
public class Category {

    private String id_category;
    private String name;
    private String categ_creator;

    public Category(String id_category, String name, String categ_creator) {
        this.id_category = id_category;
        this.name = name;
        this.categ_creator = categ_creator;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCateg_creator() {
        return categ_creator;
    }

    public void setCateg_creator(String categ_creator) {
        this.categ_creator = categ_creator;
    }
}
