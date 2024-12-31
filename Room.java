package Hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Room extends JFrame {
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private JButton confirmButton;
    private String selectedRoom;

    public Room() {
        setTitle("Room Selection");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        String[] roomTypes = {
            "Single Room",
            "Double Room",
            "Deluxe Room",
            "Family Room",
            "Junior Suite",
            "Executive Suite",
            "Accessible Room",
            "Economy Room"
        };

        String[] roomDescriptions = {
            "One bed for a single guest.\nPrice: 7000 Birr per night.",
            "One double bed or two single beds for two guests.\nPrice: 12,000 Birr per night.",
            "Upgraded decor and amenities for two guests.\nPrice: 18,000 Birr per night.",
            "Larger room with multiple beds for families.\nPrice: 25,000 Birr per night.",
            "Spacious room with a sitting area for added comfort.\nPrice: 3000 Birr per night.",
            "Separate living and sleeping areas with luxury features.\nPrice: 5000 Birr per night.",
            "Designed for guests with disabilities, featuring accessibility amenities.\nPrice: 1000 Birr per night.",
            "Basic room with minimal amenities for budget travelers.\nPrice: 500 Birr per night."
        };

        ImageIcon[] roomImages = {
            new ImageIcon("single.jpg"),
            new ImageIcon("double.jpg"),
            new ImageIcon("deluxe.jpg"),
            new ImageIcon("family.jpg"),
            new ImageIcon("junior_suite.jpg"),
            new ImageIcon("executive.jpg"),
            new ImageIcon("accessible.jpg"),
            new ImageIcon("econmy.jpg")
        };

        for (int i = 0; i < roomTypes.length; i++) {
            JButton roomButton = new JButton(roomTypes[i]);
            roomButton.setFocusPainted(false);
            roomButton.setBorder(BorderFactory.createRaisedBevelBorder());

            final String description = roomDescriptions[i];
            final ImageIcon imageIcon = roomImages[i];
            final String roomType = roomTypes[i];

            roomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayRoomDetails(description, imageIcon, roomType);
                }
            });

            roomButton.setToolTipText("Select " + roomTypes[i]);
            buttonPanel.add(roomButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());

        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(350, 120));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(350, 150));

        detailsPanel.add(scrollPane, BorderLayout.CENTER);
        detailsPanel.add(imageLabel, BorderLayout.SOUTH);

        JPanel confirmationPanel = new JPanel();
        confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRoom != null) {
                    JOptionPane.showMessageDialog(Room.this, "You have booked: " + selectedRoom);
                } else {
                    JOptionPane.showMessageDialog(Room.this, "Please select a room first.");
                }
            }
        });
        confirmationPanel.add(confirmButton);

        add(buttonPanel, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
    }

    private void displayRoomDetails(String description, ImageIcon icon, String roomType) {
        descriptionArea.setText(description);
        descriptionArea.setCaretPosition(0);
        displayImage(icon);
        selectedRoom = roomType;
    }

    private void displayImage(ImageIcon icon) {
        Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Room roomSelection = new Room();
            roomSelection.setVisible(true);
        });
    }
}
