package models;

/**
 * System billes a Client after Admin processes a Request.
 */
public class Bill {
    
    private int id;
    private int price;
    private int userId;
    private int roomId;
    private String description;
    
    public Bill() {
        this.id = 0;
        this.userId = 0;
        this.roomId = 0;
        this.price = 0;
        this.description = "";
    }
    
    public Bill(int userId, int roomId, int price, String description) {
        this.id = 0;
        this.userId = userId;
        this.roomId = roomId;
        this.price = price;
        this.description = description;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public int getRoomId() {
        return this.roomId;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
}
