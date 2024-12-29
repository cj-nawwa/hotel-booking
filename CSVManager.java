import java.io.*;
import java.util.*;

public class CSVManager {

    private static final String ROOM_FILE = "rooms.csv";
    private static final String BOOKING_FILE = "bookings.csv";

    // Save Rooms to CSV
    public static void saveRooms(List<Room> rooms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOM_FILE))) {
            writer.write("roomID,roomType,pricePerNight,occupied,bedRooms\n");  // CSV Header
            for (Room room : rooms) {
                writer.write(room + "\n");
            }
            System.out.println("Rooms saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Rooms from CSV
    public static List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOM_FILE))) {
            String line;
            reader.readLine();  // Skip Header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Room room = new Room(
                        data[0],                           // roomID
                        data[1],                           // roomType
                        Double.parseDouble(data[2]),       // pricePerNight
                        Boolean.parseBoolean(data[3]),     // occupied
                        Integer.parseInt(data[4])          // bedRooms
                );
                rooms.add(room);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Save Bookings to CSV
    public static void saveBookings(List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE))) {
            writer.write("bookingID,roomID,checkin,checkout,numOfAssociates,totalPrice,status\n");
            for (Booking booking : bookings) {
                writer.write(booking + "\n");
            }
            System.out.println("Bookings saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Bookings from CSV
    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String line;
            reader.readLine();  // Skip Header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Booking booking = new Booking(
                        data[0],                      // bookingID
                        data[1],                      // roomID
                        data[2],                      // checkin
                        data[3],                      // checkout
                        Integer.parseInt(data[4]),    // numOfAssociates
                        Double.parseDouble(data[5]),  // totalPrice
                        data[6]                       // status
                );
                bookings.add(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}