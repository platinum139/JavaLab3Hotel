package models;

/**
 * Client makes a Request to book a Room.
 */
public class Request {
    
    private int id;
    private BedsNumber bedsNumber;
    private RoomType roomType;
    private int stayTime;
    private int userId;
    
    public Request() {
        this.id = 0;
        this.bedsNumber = BedsNumber.ONE;
        this.roomType = RoomType.STANDART;
        this.stayTime = 0;
        this.userId = 0;
    }
    
    public Request(BedsNumber bedsNumber, RoomType roomType, int stayTime, int userId) {
        this.id = 0;
        this.bedsNumber = bedsNumber;
        this.roomType = roomType;
        this.stayTime = stayTime;
        this.userId = userId;
    }
    
    public String getDescription() {
        return this.roomType.toString() + " room with " +
               this.bedsNumber.toString() + " bed(s) for " +
               String.valueOf(this.stayTime) + " day(s).";
    }
    
    public int getId() {
        return this.id;
    }
        
    public BedsNumber getBedsNumber() {
        return this.bedsNumber;
    }
    
    public RoomType getRoomType() {
        return this.roomType;
    }
    
    public int getStayTime() {
        return this.stayTime;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setBedsNumber(BedsNumber bedsNumber) {
        this.bedsNumber = bedsNumber;
    }
    
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    
    public void setStayTime(int stayTime) {
        this.stayTime = stayTime;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
