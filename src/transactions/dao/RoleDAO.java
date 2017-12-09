package transactions.dao;

import transactions.connection.ConnectionManager;
import transactions.entity.Role;

import java.sql.*;
import java.util.Optional;

/**
 * Created by andrei on 27.11.17.
 */
public final class RoleDAO {
    private static RoleDAO INSTANCE = null;
    private static final String INSERT_ROLE = "insert into role (name) VALUE (?)";
    private static final String UPDATE_ROLE_BY_ID = "update role set name = ? where id_role = ?";
    private static final String GET_ROLE_BY_NAME = "select id_role, name from role where name = ?";

    private RoleDAO() {
    }

    public static RoleDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (RoleDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoleDAO();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Role> createRole(String roleName) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_ROLE);
            preparedStatement.setString(1, roleName);
            preparedStatement.addBatch();
            int[] ints = preparedStatement.executeBatch();
            preparedStatement.close();
            con.commit();
            if (ints.length > 0) {
                return Optional.of(new Role(roleName));
            }
        } catch (BatchUpdateException ex) {
            System.out.println("Ошибка при добавлении роли " + ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Role> getRoleByName(String name) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(GET_ROLE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            con.commit();
            return result(resultSet);
        } catch (BatchUpdateException ex) {
            System.out.println("Ошибка при добавлении роли " + ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Role> updateRoleNameById(int id, String name) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ROLE_BY_ID);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Role> getRoleByUsername(String username) {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement("select r.name, c.id_client from role r left JOIN client c on r.id_role = c.role_id where c.username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            con.commit();
            return result(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<Role> result(ResultSet inputResultSet) throws SQLException {
        if (inputResultSet == null) return Optional.empty();
        Role role = new Role();
        while (inputResultSet.next()) {
            role.setId_role(inputResultSet.getInt("id_role"));
            role.setName(inputResultSet.getString("name"));
        }
        return Optional.of(role);
    }
}
