package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrei on 01.12.17.
 */
public class CarDAO {
    private static CarDAO INSTANCE;

    private CarDAO() {
    }

    public static CarDAO getINSTANCE(){
        synchronized (ImageDAO.class) {
            if (INSTANCE == null) {
                return new CarDAO();
            }
        }
        return INSTANCE;
    }

    public Optional<Car> getCarById(int id){
        try(Connection con = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id_car, id_image, image, reg_number, rented, vin, brand_name, model, class_name, description, color, capacity FROM car c JOIN model m ON c.model_id = m.id_model JOIN class cl ON cl.id_class = m.class_id JOIN brand b ON b.id_brand = m.brand_id JOIN engine e ON e.id_engine = c.engine_id JOIN color ON color.id_color = c.color_id right join image i on i.car_id = c.id_car where c.id_car = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Car car = null;
            List<Integer> imagesId = null;
            boolean carGotten = false;
            while (resultSet.next()){
                if (!carGotten){
                    int idCar = Integer.parseInt(resultSet.getString("id_car"));
                    String registerNumber = resultSet.getString("reg_number");
                    boolean rented = Byte.parseByte(resultSet.getString("car_rented")) == 1 ? true: false;
                    String vin = resultSet.getString("vin");
                    String brand = resultSet.getString("model");
                    String model = resultSet.getString("model");
                    String clazz = resultSet.getString("description");
                    String color = resultSet.getString("car_color");
                    float capacity = Float.parseFloat(resultSet.getString("capacity"));
                    carGotten = !carGotten;
                    car = new Car(idCar, rented, clazz, registerNumber, vin, model, brand, color, capacity);
                    imagesId = new ArrayList<>();
                }
                imagesId.add(resultSet.getInt("id_image"));
            }
            car.setImageIds(imagesId);
            return car !=null ? Optional.of(car) : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Car getCarByName(String name){
        return null;
    }
}
