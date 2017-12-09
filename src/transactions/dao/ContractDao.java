package transactions.dao;

import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;
import transactions.connection.ConnectionManager;
import transactions.entity.Contract;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrei on 08.12.17.
 */
public final class ContractDao {
    private static String GET_ALL_RENTED_CARS_BY_CLIENT_ID =
            "SELECT id_car, reg_number, vin, brand_name, model, class_name, description, color, capacity, confirmed\n" +
                    "FROM car c JOIN model m ON c.model_id = m.id_model \n" +
                    "JOIN class cl ON cl.id_class = m.class_id \n" +
                    "JOIN brand b ON b.id_brand = m.brand_id\n" +
                    "JOIN engine e ON e.id_engine = c.engine_id \n" +
                    "JOIN color ON color.id_color = c.color_id\n" +
                    "JOIN contract on contract.car_id = c.id_car\n" +
                    "WHERE contract.client_id = ?";

    private static String GET_ALL_CONTRACTS_BY_CLIENT_ID = "select * FROM contract WHERE client_id = ?";
    private static ContractDao INSTANCE;

    private ContractDao() {
    }

    public static ContractDao getInstance() {
        synchronized (ContractDao.class) {
            if (INSTANCE == null) {
                return new ContractDao();
            }
        }
        return INSTANCE;
    }

    public Optional<Contract> createContractByCarIdAndUserId(int carId, int userId, Timestamp startDate, Timestamp endDate) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO contract (car_id, client_id, date_start, data_end, price) VALUE (?, ?, ?, ?, ?);");
            statement.setInt(1, carId);
            statement.setInt(2, userId);
            statement.setTimestamp(3, startDate);
            statement.setTimestamp(4, endDate);
            statement.setBigDecimal(5, new BigDecimal(500));
            int resultSet = statement.executeUpdate();
//            Contract contract = getContractByResult(resultSet);
            statement.close();
            return Optional.of(new Contract());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //
    public Optional<List<Contract>> getAllContractsByUserId(int clientId) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement statement = con.prepareStatement(GET_ALL_CONTRACTS_BY_CLIENT_ID);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            List<Contract> contracts = null;
            contracts = new ArrayList<>();
            while (resultSet.next()) {
                contracts.add(getContractByResult(resultSet));
            }
            return Optional.of(contracts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Contract getContractByResult(ResultSet resultSet) {
        try {
            Contract contract = new Contract(resultSet.getInt("id_contract"), resultSet.getInt("car_id"), resultSet.getInt("client_id"), resultSet.getTimestamp("date_start"), resultSet.getTimestamp("data_end"), resultSet.getBigDecimal("price"));
            contract.setComfimed(resultSet.getString("confirmed"));
            return contract;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<List<Contract>> getAllContractsNoConfirmed() {
        try(Connection con = ConnectionManager.getConnection()){
            PreparedStatement statement = con.prepareStatement("SELECT id_contract, car_id, client_id, date_start, data_end, price, confirmed FROM contract where confirmed = 'false'");
            ResultSet resultSet = statement.executeQuery();
            List<Contract> contracts = new ArrayList<>();
            while (resultSet.next()){
                contracts.add(getContractByResult(resultSet));
            }
            return Optional.of(contracts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
