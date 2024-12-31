import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomGUI {

    private JFrame frame;
    private JTextField roomIdField;
    private JComboBox<String> roomTypeDropdown;
    private JTextField pricePerNightField;
    private JTextField bedroomsField;
    private JCheckBox availabilityCheckbox;

    public RoomGUI(String roomId, String roomType, String pricePerNight, String bedrooms, boolean availability) {
        frame = new JFrame("Room Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        // Room ID
        frame.add(new JLabel("Room ID:"));
        roomIdField = new JTextField(roomId);
        roomIdField.setEditable(false); // Room ID is not editable
        frame.add(roomIdField);

        // Room Type Dropdown
        frame.add(new JLabel("Room Type:"));
        String[] roomTypes = {"Single", "Double", "Suite"};
        roomTypeDropdown = new JComboBox<>(roomTypes);
        roomTypeDropdown.setSelectedItem(roomType);
        frame.add(roomTypeDropdown);

        // Price per Night
        frame.add(new JLabel("Price per Night:"));
        pricePerNightField = new JTextField(pricePerNight);
        frame.add(pricePerNightField);

        // Number of Bedrooms
        frame.add(new JLabel("Number of Bedrooms:"));
        bedroomsField = new JTextField(bedrooms);
        frame.add(bedroomsField);

        // Availability
        frame.add(new JLabel("Available:"));
        availabilityCheckbox = new JCheckBox();
        availabilityCheckbox.setSelected(availability);
        frame.add(availabilityCheckbox);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });
        frame.add(saveButton);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void handleSaveAction() {
        String roomId = roomIdField.getText();
        String roomType = (String) roomTypeDropdown.getSelectedItem();
        String pricePerNight = pricePerNightField.getText();
        String bedrooms = bedroomsField.getText();
        boolean availability = availabilityCheckbox.isSelected();

        // Placeholder for saving action logic, to be implemented by another class
        JOptionPane.showMessageDialog(frame, "Room details captured for processing.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Example usage with sample data
        SwingUtilities.invokeLater(() -> new RoomGUI("101", "Single", "100.0", "1", true));
    }
}

