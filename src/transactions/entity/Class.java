package transactions.entity;

/**
 * Created by andrei on 27.11.17.
 */
public class Class {
    private int id_class;
    private String class_name;
    private String description;

    public Class() {
    }

    public Class(int id_class, String class_name, String description) {
        this.id_class = id_class;
        this.class_name = class_name;
        this.description = description;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


