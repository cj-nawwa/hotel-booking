public class Room {
    private String roomID;
    private String roomType;
    private double pricePerNight;
    private boolean occupied;
    private int bedRooms;
    private String[] tags;
    private boolean isAvailable = true;

    // Constructor
    public Room(String roomID, String roomType, double pricePerNight, boolean occupied, int bedRooms) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.occupied = occupied;
        this.bedRooms = bedRooms;
    }

    // Getter and Setter for roomNumber
    public String getRoomID() {
        return roomID;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomID = roomID;
    }

    // Getter and Setter for roomType
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Getter and Setter for pricePerNight
    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    // Getter and Setter for occupied
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Getter and Setter for bedRooms
    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    // Getter and Setter for tags
    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    // Getter and Setter for isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
            this.isAvailable = available;
    }
 @Override
    public String toString() {
        return String.format("%s,%s,%.2f,%b,%d", roomID, roomType, pricePerNight, occupied, bedRooms);
    }
}
