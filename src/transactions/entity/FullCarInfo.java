package transactions.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andrei on 03.12.17.
 */
public class FullCarInfo implements Serializable {

    private static final long serialVersionUID = 3317179509849585722L;

    private int idCar;
    private String model;
    private String classAuto;
    private String classDescription;
    private String brand;
    private String color;
    private float consumption;
    private boolean rented;
    private String registerNumber;
    private String vin;
    private List<Integer> imageIds;

    public FullCarInfo(int idCar, String model, String classAuto, String classDescription, String brand, String color, float consumption, boolean rented, String registerNumber, String vin, List<Integer> imageIds) {
        this.imageIds = imageIds;
        this.idCar = idCar;
        this.model = model;
        this.classAuto = classAuto;
        this.classDescription = classDescription;
        this.brand = brand;
        this.color = color;
        this.consumption = consumption;
        this.rented = rented;
        this.registerNumber = registerNumber;
        this.vin = vin;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }

    public String getModel() {
        return model;
    }

    public String getClassAuto() {
        return classAuto;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public float getConsumption() {
        return consumption;
    }

    public boolean isRented() {
        return rented;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public String getVin() {
        return vin;
    }

    public int getIdCar() {
        return idCar;
    }
}
