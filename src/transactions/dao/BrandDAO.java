package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by andrei on 27.11.17.
 */
public class BrandDAO {
    private static final String CREATE_BRAND = "insert into brand (brand_name) VALUE (?)";
    private static BrandDAO INSTANCE = null;

    public static BrandDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (BrandDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrandDAO();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Brand> create(String brandName) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement(CREATE_BRAND);
            statement.setString(1, brandName);
            int i = statement.executeUpdate();
            statement.close();
            return Optional.of(new Brand());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Brand result(ResultSet resultSet, String... fields) throws SQLException {
        Brand brand = new Brand();
        if (resultSet != null) {
            while (resultSet.next()) {
                brand.setId_brand(resultSet.getInt("id_brand"));
                brand.setBrand_name(resultSet.getString("brand_name"));
            }
        }
        return brand;
    }
}
