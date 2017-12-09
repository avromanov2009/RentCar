package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by andrei on 26.11.17.
 */
public final class UserDAO {
    private static UserDAO INSTANCE = null;
    private static final String INSERT_USER =
            "insert into `user` (name, password, role_id) VALUE (?, ?, ?)";
    private static final String GET_USER_BY_ID =
            "select id_user, name, password, role_id from user where id_user = ?";
    private static final String GET_USER_BY_NAME =
            "select id_user, role_id, name, password from user where name = ?";

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDAO();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<User> getUserByRoleName(String username){
        try (Connection con = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("select r.name, c.id_client from role r left JOIN client c on r.id_role = c.role_id where c.username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getUserByName(String name){
        try(Connection con = ConnectionManager.getConnection()){
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(GET_USER_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            con.commit();
            preparedStatement.close();
            return Optional.of(result(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return Optional.empty();
    }

    public Optional<User> getUserById(int id) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            con.commit();
            statement.close();
            return Optional.of(result(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> create(User user) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole_id());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            con.commit();
            preparedStatement.close();
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private User result(ResultSet inputResultSet) throws SQLException {
        User user = null;
        if (inputResultSet != null){
            user = new User();
        }
        while (inputResultSet.next()){
            user.setId_user(inputResultSet.getInt("id_user"));
            user.setRole_id(inputResultSet.getInt("role_id"));
            user.setName(inputResultSet.getString("name"));
            user.setPassword(inputResultSet.getString("password"));
        }
        return user;
    }
}
