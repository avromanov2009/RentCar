package transactions.dao;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import transactions.connection.ConnectionManager;
import transactions.entity.Client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by andrei on 06.12.17.
 */
public class ClientDao {
    private static ClientDao INSTANCE = null;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    public void createClient(Client client) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO client (role_id, first_name, last_name, passport, rating, address, phone_number, email, password, username) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            int roleId = client.getRole_id();
            preparedStatement.setInt(1, roleId);
            String firstName = client.getFirst_name();
            preparedStatement.setString(2, firstName);
            String lastName = client.getLast_name();
            preparedStatement.setString(3, lastName);
            String passport = client.getPassport();
            preparedStatement.setString(4, passport);
            int rating = client.getRating();
            preparedStatement.setInt(5, rating);
            String address = client.getAddress();
            preparedStatement.setString(6, address);
            String phoneNumber = client.getPhone_number();
            preparedStatement.setString(7, phoneNumber);
            String email = client.getEmail();
            preparedStatement.setString(8, email);
            String password = cryptPassword(client.getPassword());
            preparedStatement.setString(9, password);
            String username = client.getUsername();
            preparedStatement.setString(10, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Client> getClientByLogin(String login) {
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("select id_client, role_id, first_name, last_name, passport, rating, address, phone_number, email, password, username from client where username = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int role_id = Integer.parseInt(resultSet.getString("role_id"));
                int id_client = Integer.parseInt(resultSet.getString("id_client"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String passport = resultSet.getString("passport");
                int rating = Integer.parseInt(resultSet.getString("rating"));
                String address = resultSet.getString("address");
                String phone_number = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");

                return Optional.of(new Client(id_client, role_id, firstName, lastName, passport, rating, address, phone_number, email, password, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private String cryptPassword(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return HexBin.encode(sha256.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
