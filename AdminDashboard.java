import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
    private ArrayList<Booking> bookings = new ArrayList<>();
    private JTable roomTable;
    // loading all rooms and bookings use this to load the database
    private ArrayList<Room> rooms = new Room[]{
        new Room("105", "VIP",99.9,false,3),
                new Room("201", "Economic", 29.9,false, 1),
                new Room("202", "VIP", 99.9,false, 1),
                new Room("203", "Economic", 29.9,false, 1),
                new Room("204", "Economic", 29.9,false, 1),
                new Room("205", "Economic", 29.9,false, 1),
                new Room("301", "Luxury",59.9, false, 2)
    };

    public AdminDashboard(String username, String role) {
        setTitle("Admin Dashboard - " + username);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //log out button
        JButton logOutButton = new JButton("Log-out");

        // Room Table Initialization
        roomTable = new JTable(new String[0][2], new String[]{"Room Number", "Price per Night"});
        updateRoomTable();

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addRoomButton = new JButton("Add Room");
        JButton viewBookingsButton = new JButton("View All Bookings");

        // Button Actions
        addRoomButton.addActionListener(e -> openAddRoomDialog());
        viewBookingsButton.addActionListener(e -> showBookings());

        buttonPanel.add(addRoomButton);
        buttonPanel.add(viewBookingsButton);

        add(new JScrollPane(roomTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Update Table with Room Data
    private void updateRoomTable() {
        String[][] data = new String[rooms.size()][2];
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            data[i][0] = room.getRoomID();
            data[i][1] = "$" + room.getPricePerNight();
        }
        roomTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"Room Number", "Price per Night"}
        ));
    }

    // Add New Room
    private void openAddRoomDialog() {
        JDialog addDialog = new JDialog(this, "Add New Room", true);
        addDialog.setSize(300, 200);
        addDialog.setLayout(new GridLayout(3, 2));

        JTextField roomNumberField = new JTextField();
        JTextField priceField = new JTextField();

        addDialog.add(new JLabel("Room Number:"));
        addDialog.add(roomNumberField);
        addDialog.add(new JLabel("Price per Night:"));
        addDialog.add(priceField);

        JButton confirmButton = new JButton("Add Room");
        confirmButton.addActionListener(e -> {
            try {
                String roomID = roomNumberField.getText();
                double price = Double.parseDouble(priceField.getText());
                rooms.add(new Room(roomID, "VIP",price,false,3));
                updateRoomTable();
                addDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addDialog, "Invalid price. Enter a number.");
            }
        });

        addDialog.add(new JLabel());
        addDialog.add(confirmButton);
        addDialog.setVisible(true);
    }

    // Remove Selected Room
    private void removeRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            rooms.remove(selectedRow);
            updateRoomTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a room to remove.");
        }
    }

    // Set Price for Selected Room
    private void setRoomPrice() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            Room room = rooms.get(selectedRow);
            String newPrice = JOptionPane.showInputDialog(this, "Set new price for Room " + room.getRoomID(), room.getPricePerNight());
            if (newPrice != null) {
                try {
                    double price = Double.parseDouble(newPrice);
                    room.setPricePerNight(price);
                    updateRoomTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Enter a number.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a room to set price.");
        }
    }

    // View All Bookings
    private void showBookings() {
        StringBuilder bookingDetails = new StringBuilder("All Bookings:\n");
        for (Booking booking : bookings) {
            bookingDetails.append("Room: ").append(booking.getRoomID())
                    .append(" | Check-In: ").append(booking.getcheckInDate())
                    .append(" | Check-Out: ").append(booking.getcheckOutDate()).append("\n");
        }
        JOptionPane.showMessageDialog(this, bookingDetails.toString());
    }
}
