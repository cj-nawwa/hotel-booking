//import java.time.LocalDate;
//
//public class Dashboard extends Booking {
//    private String customerName; // Name of the customer
//    private String customerEmail; // Email ID of the customer
//
//    // Constructor to initialize Dashboard class
//    public Dashboard(double bookingId, LocalDate checkInDate, LocalDate checkOutDate,
//                     int numberOfGuests, double totalPrice, String status,
//                     String customerName, String customerEmail) {
//        super(bookingId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status);
//        this.customerName = customerName;
//        this.customerEmail = customerEmail;
//    }
//    // Another constructor
//    //...
//    // Method to display dashboard details
//    public void showDashboard() {
//        System.out.println("Customer Dashboard");
//        System.out.println("------------------");
//        System.out.println("Customer Name: " + customerName);
//        System.out.println("Customer Email: " + customerEmail);
//        bookdetail(); // Calls the method from the parent class to display booking details
//        // We will implement the Gui here
//    }
//
//    // Getters and setters for customerName and customerEmail
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCustomerEmail() {
//        return customerEmail;
//    }
//
//    public void setCustomerEmail(String customerEmail) {
//        this.customerEmail = customerEmail;
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Dashboard extends JFrame {
    private JTable roomTable;
    private ArrayList<String[]> bookings;
    private Room[] rooms;

    public Dashboard(String username, String role) {
        // Frame settings
        setTitle("Dashboard - " + role);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // User Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(new JLabel("Username: " + username, JLabel.CENTER));
        infoPanel.add(new JLabel("Role: " + role, JLabel.CENTER));
        add(infoPanel, BorderLayout.NORTH);

        // Initialize room data: this will be provided by the database we have. for the purpose of testing lets ceate random rooms
        rooms = new Room[]{
                new Room("007", "VIP",99.9,false,3),
                new Room("202", "Economic", 29.9,false, 1),
                new Room("303", "Luxury",59.9, false, 2)
        };

        bookings = new ArrayList<>();

        // Room Booking Panel (For Guests)
        if (role.equals("Guest")) {
            String[] columnNames = {"Room", "Check-In", "Check-Out"};
            roomTable = new JTable(new String[0][3], columnNames);
            add(new JScrollPane(roomTable), BorderLayout.CENTER);

            JButton bookRoomButton = new JButton("Book a Room");
            bookRoomButton.addActionListener(e -> openBookingDialog());
            add(bookRoomButton, BorderLayout.SOUTH);
        } else {
            JLabel adminLabel = new JLabel("Welcome Admin! No room details to display.", JLabel.CENTER);
            adminLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            add(adminLabel, BorderLayout.CENTER);
        }

        setVisible(true);
    }

    // Opens Booking Dialog
    private void openBookingDialog() {
        JDialog bookingDialog = new JDialog(this, "Book a Room", true);
        bookingDialog.setSize(350, 250);
        bookingDialog.setLayout(new GridLayout(5, 2));
        bookingDialog.setLocationRelativeTo(this);

        // Room Dropdown
        bookingDialog.add(new JLabel("Select Room:"));
        JComboBox<String> roomSelector = new JComboBox<>();
        for (Room room : rooms) {
            roomSelector.addItem(room.getRoomNumber() + " - $" + room.getPricePerNight() + "/night");
        }
        bookingDialog.add(roomSelector);

        // Check-in and Check-out
        JTextField checkInField = new JTextField();
        JTextField checkOutField = new JTextField();
        bookingDialog.add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        bookingDialog.add(checkInField);
        bookingDialog.add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        bookingDialog.add(checkOutField);

        // Confirm Button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(e -> {
            try {
                LocalDate checkIn = LocalDate.parse(checkInField.getText());
                LocalDate checkOut = LocalDate.parse(checkOutField.getText());

                if (checkOut.isAfter(checkIn)) {
                    int selectedRoomIndex = roomSelector.getSelectedIndex();
                    Room selectedRoom = rooms[selectedRoomIndex];

                    long days = ChronoUnit.DAYS.between(checkIn, checkOut);
                    double totalPrice = days * selectedRoom.getPricePerNight();

                    // Update bookings and table
                    bookings.add(new String[]{
                            selectedRoom.getRoomNumber(),
                            checkIn.toString(),
                            checkOut.toString()
                    });

                    updateTable();
                    JOptionPane.showMessageDialog(this, "Room Booked! Total: $" + totalPrice);
                    bookingDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(bookingDialog, "Check-out date must be after check-in.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookingDialog, "Invalid date format. Use YYYY-MM-DD.");
            }
        });

        bookingDialog.add(new JLabel());
        bookingDialog.add(confirmButton);

        bookingDialog.setVisible(true);
    }

    // Updates Room Table after Booking
    private void updateTable() {
        String[][] data = bookings.toArray(new String[0][3]);
        roomTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"Room", "Check-In", "Check-Out"}
        ));
    }
            }
