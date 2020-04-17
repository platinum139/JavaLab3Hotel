package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.BedsNumber;
import models.Request;
import models.RoomType;

/**
 * This DAO class provides CRUD database operations for the table Request.
 */
public class RequestDAO {
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public RequestDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertRequest(Request request) throws SQLException {
        
        String sql = "INSERT INTO requests (roomType, bedsNumber, stayTime, userId) VALUES (?, ?, ?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, request.getRoomType().toString());
        statement.setString(2, request.getBedsNumber().toString());
        statement.setInt(3, request.getStayTime());
        statement.setInt(4, request.getUserId());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Request> listAllRequests() throws SQLException {
        
        List<Request> listRequests = new ArrayList<>();
         
        String sql = "SELECT * FROM requests";
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int stayTime = resultSet.getInt("stayTime");
            int userId = resultSet.getInt("userId");
            
            Request request = new Request(bedsNumber,roomType, stayTime, userId);
            request.setId(id);
            listRequests.add(request);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRequests;
    }
    
    public List<Request> listUserRequests(int userId) throws SQLException {
        
        List<Request> listRequests = new ArrayList<>();
        
        String sql = "SELECT * FROM requests WHERE userId = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);
        
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int stayTime = resultSet.getInt("stayTime");
            
            Request request = new Request(bedsNumber,roomType, stayTime, userId);
            request.setId(id);
            listRequests.add(request);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRequests;
    }
    
    public boolean deleteRequest(int id) throws SQLException {
        
        String sql = "DELETE FROM requests WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public Request getRequest(int id) throws SQLException {
        
        Request request = null;
        String sql = "SELECT * FROM requests WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int stayTime = resultSet.getInt("stayTime");
            int userId = resultSet.getInt("userId");
            
            request = new Request(bedsNumber,roomType, stayTime, userId);
            request.setId(id);
        }
         
        resultSet.close();
        statement.close();
         
        return request;
    }
}
