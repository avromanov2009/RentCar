package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.FullCarInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ViewCarsDao {
    private static ViewCarsDao INSTANCE;
    private static String GET_ALL_IMAGES_BY_CAR_ID_QUERY = "SELECT id_car, id_image, image FROM car c right join image i on i.car_id = c.id_car where c.id_car = ?";
    private static String GET_ALL_CARS_QUERY = "SELECT id_car, rented, reg_number, vin, brand_name, model, class_name, description, color, capacity FROM car c JOIN model m ON c.model_id = m.id_model JOIN class cl ON cl.id_class = m.class_id JOIN brand b ON b.id_brand = m.brand_id JOIN engine e ON e.id_engine = c.engine_id JOIN color ON color.id_color = c.color_id";
    private static String GET_CAR_BY_ID_QUERY = "SELECT id_car, id_image, image, reg_number, rented, vin, brand_name, model, class_name, description, color, capacity FROM car c JOIN model m ON c.model_id = m.id_model JOIN class cl ON cl.id_class = m.class_id JOIN brand b ON b.id_brand = m.brand_id JOIN engine e ON e.id_engine = c.engine_id JOIN color ON color.id_color = c.color_id right join image i on i.car_id = c.id_car where c.id_car = ?";

    private ViewCarsDao() {
    }

    public static ViewCarsDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ModelDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewCarsDao();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<List<FullCarInfo>> getAllCars(){
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(GET_ALL_CARS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<FullCarInfo> cars = new ArrayList<>();
            while (resultSet.next()){
                FullCarInfo carInfo = getFullInfoByCarId(Integer.parseInt(resultSet.getString("id_car"))).get();
                cars.add(carInfo);
            }
            return Optional.of(cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<FullCarInfo> getFullInfoByCarId(int id_car){
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(GET_CAR_BY_ID_QUERY);
            preparedStatement.setInt(1, id_car);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null){
                return mapToFullCarInfo(resultSet);
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<FullCarInfo> mapToFullCarInfo(ResultSet result) throws SQLException {
        if (result == null){
            return Optional.empty();
        }
        result.next();
        FullCarInfo carInfo = null;
        int idCar = Integer.parseInt(result.getString("id_car"));
        String model = result.getString("model");
        String classAuto = result.getString("class_name");
        String classDescription = result.getString("description");
        String brand = result.getString("brand_name");
        String color = result.getString("color");
        float consumption = Float.parseFloat(result.getString("capacity"));
        boolean rented = Byte.parseByte(result.getString("rented")) != 1 ? false : true;
        String registerNumber = result.getString("reg_number");
        String vin = result.getString("vin");
        List<Integer> images = new ArrayList<>();
        images.add(Integer.parseInt(result.getString("id_image")));
        while (result.next()){
            images.add(Integer.parseInt(result.getString("id_image")));
        }
        return Optional.of(new FullCarInfo(idCar, model, classAuto, classDescription, brand, color, consumption, rented, registerNumber, vin, images));
    }
}