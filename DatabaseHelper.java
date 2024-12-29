import java.sql.*;

public class DatabaseHelper {

    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yourpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    abstract class DatabaseFunctions {
        abstract void create(); 
        abstract void update();
        abstract void delete();
    }

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
            try (Connection conn = getConnection()) {
                String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    stmt.setString(3, role);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void update() {
            try (Connection conn = getConnection()) {
                String query = "UPDATE users SET password = ? WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, password);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void delete() {
            try (Connection conn = getConnection()) {
                String query = "DELETE FROM users WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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
            try (Connection conn = getConnection()) {
                String query = "INSERT INTO rooms (room_number, room_type, price_per_night) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, roomNumber);
                    stmt.setString(2, roomType);
                    stmt.setDouble(3, price);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void update() {
            try (Connection conn = getConnection()) {
                String query = "UPDATE rooms SET price_per_night = ? WHERE room_number = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setDouble(1, price);
                    stmt.setString(2, roomNumber);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void delete() {
            try (Connection conn = getConnection()) {
                String query = "DELETE FROM rooms WHERE room_number = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, roomNumber);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    class BookingManager extends DatabaseFunctions {
        private int userId;
        private int roomId;
        private String startDate;
        private String endDate;
        private double totalPrice;

        public BookingManager(int userId, int roomId, String startDate, String endDate) {
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
            try (Connection conn = getConnection()) {
                String query = "INSERT INTO bookings (user_id, room_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, userId);
                    stmt.setInt(2, roomId);
                    stmt.setString(3, startDate);
                    stmt.setString(4, endDate);
                    stmt.setDouble(5, totalPrice);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void update() {
            try (Connection conn = getConnection()) {
                String query = "UPDATE bookings SET start_date = ?, end_date = ?, total_price = ? WHERE user_id = ? AND room_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, startDate);
                    stmt.setString(2, endDate);
                    stmt.setDouble(3, totalPrice);
                    stmt.setInt(4, userId);
                    stmt.setInt(5, roomId);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        void delete() {
            try (Connection conn = getConnection()) {
                String query = "DELETE FROM bookings WHERE user_id = ? AND room_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, userId);
                    stmt.setInt(2, roomId);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
