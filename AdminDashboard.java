imort javax.swing.*;

import java.awt.*;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
    private ArrayList<Booking> bookings = new ArrayList<>();
    private JTable roomGridPanel;
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

            // Update Grid with Room Buttons
    private void updateRoomGrid() {
        roomGridPanel.removeAll();
        for (Room room : rooms) {
            JButton roomButton = new JButton("<html>" + room.getRoomNumber() + "<br>$" + room.getPricePerNight() + "</html>");
            roomButton.setFont(new Font("Arial", Font.BOLD, 14));
            roomButton.setBackground(room.isAvailable() ? Color.GREEN : Color.RED);
            roomButton.addActionListener(e -> openEditRoomDialog(room));
            roomGridPanel.add(roomButton);
        }
        roomGridPanel.revalidate();
        roomGridPanel.repaint();
    }


        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton addRoomButton = new JButton("Add Room");
        JButton viewBookingsButton = new JButton("View All Bookings");

        // Button Actions
        addRoomButton.addActionListener(e -> openAddRoomDialog());
        viewBookingsButton.addActionListener(e -> showBookings());

        buttonPanel.add(addRoomButton);
        buttonPanel.add(viewBookingsButton);

        add(new JScrollPane(roomGridPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    
private void updateRoomGrid() {
        roomGridPanel.removeAll();
        for (Room room : rooms) {
            JButton roomButton = new JButton("<html>" + room.getRoomID() + "<br>$" + room.getPricePerNight() + "</html>");
            roomButton.setFont(new Font("Arial", Font.BOLD, 14));
            roomButton.setBackground(room.isAvailable() ? Color.GREEN : Color.RED);
            roomButton.addActionListener(e -> openEditRoomDialog(room));
            roomGridPanel.add(roomButton);
        }
        roomGridPanel.revalidate();
        roomGridPanel.repaint();
    }

    // Add New Room Dialog
    private void openAddRoomDialog() {
        JDialog addDialog = new JDialog(this, "Add New Room", true);
        addDialog.setSize(300, 300);
        addDialog.setLayout(new GridLayout(4, 2));

        JTextField roomNumberField = new JTextField();
        JTextField priceField = new JTextField();
        JComboBox<String> roomTypeBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});

        addDialog.add(new JLabel("Room Number:"));
        addDialog.add(roomNumberField);
        addDialog.add(new JLabel("Price per Night:"));
        addDialog.add(priceField);
        addDialog.add(new JLabel("Room Type:"));
        addDialog.add(roomTypeBox);

        JButton confirmButton = new JButton("Add Room");
        confirmButton.addActionListener(e -> {
            try {
                String roomNumber = roomNumberField.getText();
                double price = Double.parseDouble(priceField.getText());
                String roomType = (String) roomTypeBox.getSelectedItem();
                ////
///need changes here and we have 1 bed room for now (by default)
                rooms.add(new Room(roomNumber,roomType, price, false, 1));
                updateRoomGrid();
                addDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addDialog, "Invalid price.");
            }
        });

        addDialog.add(new JLabel());
        addDialog.add(confirmButton);
        addDialog.setVisible(true);
    }

    // Edit Room Dialog
    private void openEditRoomDialog(Room room) {
        JDialog editDialog = new JDialog(this, "Edit Room: " + room.getRoomNumber(), true);
        editDialog.setSize(350, 350);
        editDialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField(room.getRoomID());
        JTextField priceField = new JTextField(String.valueOf(room.getPricePerNight()));
        JComboBox<String> roomTypeBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        roomTypeBox.setSelectedItem(room.getRoomType());
        JCheckBox availabilityCheck = new JCheckBox("Available", room.isAvailable());

        editDialog.add(new JLabel("Room Number:"));
        editDialog.add(nameField);
        editDialog.add(new JLabel("Price per Night:"));
        editDialog.add(priceField);
        editDialog.add(new JLabel("Room Type:"));
        editDialog.add(roomTypeBox);
        editDialog.add(new JLabel("Available:"));
        editDialog.add(availabilityCheck);

        // Confirm Button for Saving Changes
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                room.setRoomID(nameField.getText());
                room.setpricePerNight(Double.parseDouble(priceField.getText()));
                room.setRoomType((String) roomTypeBox.getSelectedItem());
                room.setAvailable(availabilityCheck.isSelected());
                updateRoomGrid();
                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "Invalid input.");
            }
        });

        // Delete Room Button
        JButton deleteButton = new JButton("Delete Room");
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(editDialog, "Are you sure you want to delete this room?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                rooms.remove(room);
                updateRoomGrid();
                editDialog.dispose();
            }
        });

        editDialog.add(saveButton);
        editDialog.add(deleteButton);
        editDialog.setVisible(true);
    }
    }
}