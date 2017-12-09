package transactions.entity;

/**
 * Created by andrei on 27.11.17.
 */
public class Model {
    private int id_model;
    private int class_id;
    private int brand_id;
    private String model;

    public Model() {
    }

    public Model(int id_model, int class_id, int brand_id, String model) {
        this.id_model = id_model;
        this.class_id = class_id;
        this.brand_id = brand_id;
        this.model = model;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
