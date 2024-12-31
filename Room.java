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
/*
Single Room
One bed for a single guest.
Price: $70 per night.
Double Room
One double bed or two single beds for two guests.
Price: $120 per night.
Deluxe Room
Upgraded decor and amenities for two guests.
Price: $180 per night.
Family Room
Larger room with multiple beds for families.
Price: $250 per night.
Junior Suite
Spacious room with a sitting area for added comfort.
Price: $300 per night.
Executive Suite
Separate living and sleeping areas with luxury features.
Price: $500 per night.
Accessible Room
Designed for guests with disabilities, featuring accessibility amenities.
Price: $100 per night.
Economy Room
Basic room with minimal amenities for budget travelers.
Price: $50 per night.
*/
