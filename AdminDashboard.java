import java.util.ArrayList;
import java.util.List;
//allows the use of dynamic arrays
public class AdminDashboard {
    private List<Booking> bookings;
    private List<Room> rooms;

    public AdminDashboard() {
        this.bookings = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    // Method to display all bookings
    public void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }
        System.out.println("Current Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    // Method to add a new booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
        System.out.println("Booking added successfully.");
    }

    // Method to remove a booking
    public void removeBooking(Booking booking) {
        if (bookings.remove(booking)) {
            System.out.println("Booking removed successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    // Method to display all rooms
    public void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // Method to add a new room
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room added successfully.");
    }

    // Method to remove a room
    public void removeRoom(Room room) {
        if (rooms.remove(room)) {
            System.out.println("Room removed successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }
}

// Sample Booking class
class Booking {
    private String guestName;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;

    public Booking(String guestName, String roomType, String checkInDate, String checkOutDate) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Override toString() for displaying booking details
    @Override
    public String toString() {
        return "Booking{" +
                "guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                '}';
    }
}

// Sample Room class
class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;

    public Room(String roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    // Override toString() for displaying room details
    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
