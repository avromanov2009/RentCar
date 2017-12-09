package transactions.entity;

import java.util.List;

/**
 * Created by andrei on 27.11.17.
 */
public class Car {
    private int id_car;
    private boolean rented;
    private String clazz;
    private String brand;
    private String reg_number;
    private String vin;
    private String model;
    private String color;
    private float engine;
    private List<Integer> imageIds;
    public Car() {
    }

    public Car(int id_car, boolean rented, String clazz, String reg_number, String vin, String model, String brand, String color, float engine) {
        this.id_car = id_car;
        this.rented = rented;
        this.clazz = clazz;
        this.reg_number = reg_number;
        this.vin = vin;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.brand = brand;
    }

    public int getId_car() {
        return id_car;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isRented() {
        return rented;
    }

    public String getClazz() {
        return clazz;
    }

    public String getReg_number() {
        return reg_number;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public float getEngine() {
        return engine;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }
}
