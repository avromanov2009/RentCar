package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.Car;
import transactions.entity.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrei on 28.11.17.
 */
public final class ImageDAO {
    private static ImageDAO INSTANCE = null;

    private ImageDAO() {
    }

    public static ImageDAO getInstance() {
        synchronized (ImageDAO.class) {
            if (INSTANCE == null) {
                return new ImageDAO();
            }
        }
        return INSTANCE;
    }

    public List<Image> getImagesByCar(Car car) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement("select path, image from image where car_id = ?");
            statement.setInt(1, car.getId_car());
            ResultSet resultSet = statement.executeQuery();
            List<Image> images = new ArrayList<>();

            while (resultSet.next()) {
                Blob blob = resultSet.getBlob("image");
                blob.getBytes(1, (int) blob.length());
                resultSet.getString("path");
            }
            resultSet.close();
            statement.close();
            return images;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Image> getImageById(int id){
        try(Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT path, image FROM image where id_image = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Blob image = resultSet.getBlob("image");
                Image image1 = new Image();
                image1.setImage(image.getBytes(1, (int) image.length()));
                image1.setId_image(id);
                image1.setPath(resultSet.getString("path"));
                return Optional.of(image1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Image> getImagesByCarId(int car_id) {
        List<Image> images = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement("select path, image from image where car_id = ?");
            statement.setInt(1, car_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Blob blob = resultSet.getBlob("image");
//                System.out.println(blob.getBinaryStream());
                byte[] data = blob.getBytes(1, (int) blob.length());
                Image img = new Image();
                img.setImage(data);
                img.setPath(resultSet.getString("path"));
                images.add(img);
            }
            resultSet.close();
            statement.close();
            return images;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int createImageForCar(String path, InputStream image, Car car) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO image (path, image, car_id) VALUE (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, path);
            statement.setBlob(2, image);
            statement.setInt(3, car.getId_car());
            statement.addBatch();
            statement.executeBatch();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении картинки. " + e.getMessage());
            return -1;
        }
    }
}
