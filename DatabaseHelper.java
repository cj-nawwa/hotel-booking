import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class DatabaseHelper {

    // File paths for CSVs
    private static final String USERS_FILE = "users.csv";
    private static final String ROOMS_FILE = "rooms.csv";
    private static final String BOOKINGS_FILE = "bookings.csv";

    // Abstract base class for database functions
    abstract class DatabaseFunctions {
        abstract void create();
        abstract void update();
        abstract void delete();
        abstract List<String> readAll();
    }

    // Manager class (User management)
    class Manager extends DatabaseFunctions {
        private String username;
        private String password;
        private String role;

        public Manager(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        @Override
        void create() {
            appendToFile(USERS_FILE, String.join(",", username, password, role));
        }

        @Override
        void update() {
            modifyFile(USERS_FILE, line -> {
                String[] fields = line.split(",");
                if (fields[0].equals(username)) {
                    fields[1] = password; // Update password
                    fields[2] = role; // Ensure role is updated as well
                    return String.join(",", fields);
                }
                return line;
            });
        }

        @Override
        void delete() {
            removeFromFile(USERS_FILE, line -> line.split(",")[0].equals(username));
        }

        @Override
        List<String> readAll() {
            return readFile(USERS_FILE);
        }
    }

    // Room Manager class
    class RoomManager extends DatabaseFunctions {
        private String roomNumber;
        private String roomType;
        private double price;

        public RoomManager(String roomNumber, String roomType, double price) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.price = price;
        }

        @Override
        void create() {
            appendToFile(ROOMS_FILE, String.join(",", roomNumber, roomType, String.valueOf(price)));
        }

        @Override
        void update() {
            modifyFile(ROOMS_FILE, line -> {
                String[] fields = line.split(",");
                if (fields[0].equals(roomNumber)) {
                    fields[1] = roomType;
                    fields[2] = String.valueOf(price); // Update price
                    return String.join(",", fields);
                }
                return line;
            });
        }

        @Override
        void delete() {
            removeFromFile(ROOMS_FILE, line -> line.split(",")[0].equals(roomNumber));
        }

        @Override
        List<String> readAll() {
            return readFile(ROOMS_FILE);
        }
    }

    // Booking Manager class
    class BookingManager extends DatabaseFunctions {
        private String userId;
        private String roomId;
        private String startDate;
        private String endDate;
        private double totalPrice;

        public BookingManager(String userId, String roomId, String startDate, String endDate) {
            this.userId = userId;
            this.roomId = roomId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.totalPrice = calculateTotalPrice();
        }

        private double calculateTotalPrice() {
            return 100.0 * (java.time.LocalDate.parse(endDate).toEpochDay() - java.time.LocalDate.parse(startDate).toEpochDay());
        }

        @Override
        void create() {
            appendToFile(BOOKINGS_FILE, String.join(",", userId, roomId, startDate, endDate, String.valueOf(totalPrice)));
        }

        @Override
        void update() {
            modifyFile(BOOKINGS_FILE, line -> {
                String[] fields = line.split(",");
                if (fields[0].equals(userId) && fields[1].equals(roomId)) {
                    fields[2] = startDate;
                    fields[3] = endDate;
                    fields[4] = String.valueOf(totalPrice); // Update booking details
                    return String.join(",", fields);
                }
                return line;
            });
        }

        @Override
        void delete() {
            removeFromFile(BOOKINGS_FILE, line -> {
                String[] fields = line.split(",");
                return fields[0].equals(userId) && fields[1].equals(roomId);
            });
        }

        @Override
        List<String> readAll() {
            return readFile(BOOKINGS_FILE);
        }
    }

    // Utility methods for file operations

    private void appendToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Added: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyFile(String filePath, java.util.function.Function<String, String> modifier) {
        List<String> lines = readFile(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(modifier.apply(line));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeFromFile(String filePath, Predicate<String> filter) {
        List<String> lines = readFile(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                if (!filter.test(line)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


/*
the SQL code used to create the tables and link them with eachother using foreign keys in postgreSQL that were linked to the database class is as follows 
Users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('admin', 'guest')) NOT NULL);
Rooms table
CREATE TABLE rooms (
    room_id SERIAL PRIMARY KEY,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    availability BOOLEAN DEFAULT TRUE);
Bookings table
CREATE TABLE bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE);
*/
    
