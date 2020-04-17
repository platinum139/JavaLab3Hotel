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
import models.Bill;
import models.Request;
import models.Room;
import models.RoomType;

/**
 * This DAO class provides CRUD database operations for the table Rooms.
 */
public class RoomDAO {
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    
    public RoomDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
    public Room getRoom(int id) throws SQLException {
        
        Room room = null;
        
        String sql = "SELECT * FROM rooms WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int price = resultSet.getInt("price");
            boolean isAvailable = resultSet.getBoolean("isAvailable");
            int userId = resultSet.getInt("userId");
            
            room = new Room(id, bedsNumber, roomType, price, isAvailable, userId);
        }
         
        resultSet.close();
        statement.close();
         
        return room;
    }
    
    public List<Room> listAllRooms() throws SQLException {
        
        List<Room> listRooms = new ArrayList<>();
         
        String sql = "SELECT * FROM rooms";
        connect();
        
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int price = resultSet.getInt("price");
            boolean isAvailable = resultSet.getBoolean("isAvailable");
            int userId = resultSet.getInt("userId");
            
            Room room = new Room(id, bedsNumber, roomType, price, isAvailable, userId);
            listRooms.add(room);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRooms;
    }
    
    public List<Room> getSuitableRooms(Request request) throws SQLException {
        
        List<Room> rooms = new ArrayList<>();
        
        String sql = "SELECT * FROM rooms WHERE roomType = ? AND bedsNumber = ? AND isAvailable = 1";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, request.getRoomType().toString());
        statement.setString(2, request.getBedsNumber().toString());
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomType"));
            BedsNumber bedsNumber = BedsNumber.valueOf(resultSet.getString("bedsNumber"));
            int price = resultSet.getInt("price");
            boolean isAvailable = resultSet.getBoolean("isAvailable");
            int userId = resultSet.getInt("userId");
            
            Room room = new Room(id, bedsNumber, roomType, price, isAvailable, userId);
            rooms.add(room);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
        return rooms;
    }
    
    public boolean updateRoom(int id, boolean isAvailable, int userId) throws SQLException {
        
        String sql = "UPDATE rooms SET isAvailable = ?, userId = ? WHERE id = ?;";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setBoolean(1, isAvailable);
        statement.setInt(2, userId);
        statement.setInt(3, id);
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
}
