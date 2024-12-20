public class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean occupied;
    private int bedRooms;
    private String[] tags;
    private boolean isAvailable = true;

    // Constructor
    public Room(int roomNumber, String roomType, double pricePerNight, boolean occupied, int bedRooms, String[] tags) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.occupied = occupied;
        this.bedRooms = bedRooms;
        this.tags = tags;
    }

    // Getter and Setter for roomNumber
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public void setAvailable(User user, boolean available) {
        if (user.getRole().equals("admin")) {
            this.isAvailable = available;
        }
    }
}
