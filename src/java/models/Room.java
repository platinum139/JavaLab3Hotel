package models;

/**
 * Admin services a Request and chooses the most suitable room.
 */
public class Room {
    
    private int id;
    private RoomType roomType;
    private BedsNumber bedsNumber;
    private int price;
    private boolean isAvailable;
    private int userId;
    
    public Room() {
        this.id = 0;
        this.bedsNumber = BedsNumber.ONE;
        this.roomType = RoomType.STANDART;
        this.price = 250;
        this.isAvailable = true;
        this.userId = 0;
    }
    
    public Room(int id, BedsNumber bedsNumber, RoomType roomType, int price, boolean isAvailable, int userId) {
        this.id = id;
        this.roomType = roomType;
        this.bedsNumber = bedsNumber;
        this.price = price;
        this.isAvailable = isAvailable;
        this.userId = 0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public RoomType getRoomType() {
        return this.roomType;
    }
    
    public BedsNumber getBedsNumber() {
        return this.bedsNumber;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public boolean getIsAvailable() {
        return this.isAvailable;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    public void setBedsNumber(BedsNumber bedsNumber) {
        this.bedsNumber = bedsNumber;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public String getDescription() {
        return this.roomType.toString() + " room with " +
               this.bedsNumber.toString() + " bed(s). Price for one night is " +
               String.valueOf(this.price) + " cu.";
    }
}
