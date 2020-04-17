package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;


/**
 * This DAO class provides CRUD database operations for the table User.
 */
public class UserDAO {
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertUser(User user) throws SQLException {
        
        String sql = "INSERT INTO users (firstname, lastname, surname, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getRole());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<User> listAllUsers() throws SQLException {
        
        List<User> listUsers = new ArrayList<>();
         
        String sql = "SELECT * FROM users";
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            
            User user = new User(id, firstname, lastname, surname, email, password, role);
            listUsers.add(user);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listUsers;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        
        String sql = "DELETE FROM users WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET email = ?, password = ? WHERE id = ?;";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setFloat(3, user.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public User getUser(int id) throws SQLException {
        
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            
            user = new User(id, firstname, lastname, surname, email, password, role);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
         
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String surname = resultSet.getString("surname");
            String role = resultSet.getString("role");
            
            user = new User(id, firstname, lastname, surname, email, password, role);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
}