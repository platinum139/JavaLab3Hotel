package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Bill;

/**
 * This DAO class provides CRUD database operations for the table Bill.
 */
public class BillDAO {
    
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    
    public BillDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
    public boolean insertBill(Bill bill) throws SQLException {
        
        String sql = "INSERT INTO bills (id, userId, roomId, price, description) VALUES (?, ?, ?, ?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, bill.getId());
        statement.setInt(2, bill.getUserId());
        statement.setInt(3, bill.getRoomId());
        statement.setInt(4, bill.getPrice());
        statement.setString(5, bill.getDescription());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public boolean deleteBill(int billId) throws SQLException {
        
        String sql = "DELETE FROM bills WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, billId);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public Bill getBill(int id) throws SQLException {
        
        Bill bill = null;
        
        String sql = "SELECT * FROM bills WHERE id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            int userId = resultSet.getInt("userId");
            int roomId = resultSet.getInt("roomId");
            int price = resultSet.getInt("price");
            String description = resultSet.getString("description");
            
            bill = new Bill(userId, roomId, price, description);
            bill.setId(id);
        }
         
        resultSet.close();
        statement.close();
         
        return bill;
    }
    
    public List<Bill> listUserBills(int userId) throws SQLException {
        
        List<Bill> listBills = new ArrayList<>();
        
        String sql = "SELECT * FROM bills WHERE userId = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);
        
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int roomId = resultSet.getInt("roomId");
            int price = resultSet.getInt("price");
            String description = resultSet.getString("description");
            
            Bill bill = new Bill(userId, roomId, price, description);
            bill.setId(id);
            listBills.add(bill);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBills;
    }
}
