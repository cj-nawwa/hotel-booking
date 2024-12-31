import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class RoomSelectionApp {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int pageIndex = 0;
    private JButton nextButton;

    // Room types and their descriptions
    private final String[] roomTypes = {"Deluxe", "Standard", "Suite", "Economy", "Penthouse", "Single"};
    private final String[] roomDescriptions = {
            "Deluxe room with king-sized bed and city view.",
            "Standard room with queen-sized bed and garden view.",
            "Luxury suite with separate living area and balcony.",
            "Affordable economy room with basic amenities.",
            "Exclusive penthouse with panoramic views and private pool.",
            "Cozy single room perfect for solo travelers."
    };

    // Room IDs for each room type
    private final String[][] roomDetails = {
            {"101", "102", "103"},
            {"201", "202", "203"},
            {"301", "302", "303"},
            {"401", "402", "403"},
            {"501", "502", "503"},
            {"601", "602", "603"}
    };

    // Images (file names) corresponding to the rooms
    private final String[][] roomImages = {
            {"deluxe1.jpg", "deluxe2.jpg", "deluxe3.jpg"},
            {"standard1.jpg", "standard2.jpg", "standard3.jpg"},
            {"suite1.jpg", "suite2.jpg", "suite3.jpg"},
            {"economy1.jpg", "economy2.jpg", "economy3.jpg"},
            {"penthouse1.jpg", "penthouse2.jpg", "penthouse3.jpg"},
            {"single1.jpg", "single2.jpg", "single3.jpg"}
    };

    // Additional services checkboxes and prices
    private final String[] serviceNames = {"WiFi", "Breakfast", "Airport Pickup"};
    private final double[] servicePrices = {10.0, 20.0, 30.0};
    private JCheckBox[] serviceCheckBoxes;
    private JLabel totalPriceLabel;

    private String selectedRoom;
    private double roomBasePrice;

    public RoomSelectionApp() {
        frame = new JFrame("Room Selection App");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize the next button
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToNextPage();
            }
        });

        // Page 1: Room Types with Descriptions
        JPanel page1 = new JPanel();
        page1.setLayout(new GridLayout(roomTypes.length + 1, 1)); // Adding extra space for next button
        for (int i = 0; i < roomTypes.length; i++) {
            final int index = i;  // Make index final to be used inside lambda
            JButton roomButton = new JButton(roomTypes[i] + ": " + roomDescriptions[i]);
            roomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectRoomType(index);
                }
            });
            page1.add(roomButton);
        }
        page1.add(nextButton);

        // Page 2: List of Rooms for Selected Type
        JPanel page2 = new JPanel();
        page2.setLayout(new GridLayout(4, 2));  // Adjust layout as per the number of rooms
        page2.add(nextButton);  // Next button at the bottom

        // Page 3: Additional Services and Grand Total
        JPanel page3 = new JPanel();
        page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));  // Vertical layout for services
        serviceCheckBoxes = new JCheckBox[servicePrices.length];
        for (int i = 0; i < servicePrices.length; i++) {
            serviceCheckBoxes[i] = new JCheckBox(serviceNames[i] + " - $" + servicePrices[i]);
            serviceCheckBoxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    updateTotalPrice();
                }
            });
            page3.add(serviceCheckBoxes[i]);
        }

        totalPriceLabel = new JLabel("Total Price: $0.00");
        page3.add(totalPriceLabel);

        // Book button to finalize the booking
        JButton bookButton = new JButton("Book Room");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookRoom();
            }
        });
        page3.add(bookButton);
        page3.add(nextButton);  // Next button at the bottom

        // Add pages to the main panel (for CardLayout)
        mainPanel.add(page1, "Page 1");
        mainPanel.add(page2, "Page 2");
        mainPanel.add(page3, "Page 3");

        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Handle going to the next page
    private void goToNextPage() {
        if (pageIndex < 2) {
            pageIndex++;
            cardLayout.next(mainPanel);
        }
    }

    // Handle room type selection
    private void selectRoomType(int index) {
        selectedRoom = roomTypes[index];
        roomBasePrice = 100.0;  // Base room price (for simplicity)

        // Move to Page 2
        JPanel page2 = (JPanel) mainPanel.getComponent(1);

        // Clear the existing rooms from Page 2
        page2.removeAll();

        // Display rooms for the selected type on Page 2
        for (int i = 0; i < roomDetails[index].length; i++) {
            final int roomIndex = i;
            JButton roomButton = new JButton("Room " + roomDetails[index][i]);
            roomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showRoomDetails(index, roomIndex);
                }
            });
            page2.add(roomButton);
        }

        // Revalidate and repaint Page 2
        page2.revalidate();
        page2.repaint();

        // Move to Page 2
        goToNextPage();
    }

    // Show selected room details (for simplicity, just print details)
    private void showRoomDetails(int roomTypeIndex, int roomIndex) {
        String selectedRoom = roomDetails[roomTypeIndex][roomIndex];
        JOptionPane.showMessageDialog(frame, "You selected " + selectedRoom + " from the " +
                roomTypes[roomTypeIndex] + " type.");
    }

    // Update the total price based on selected services
    private void updateTotalPrice() {
        double totalPrice = roomBasePrice;  // Base room price
        for (JCheckBox checkBox : serviceCheckBoxes) {
            if (checkBox.isSelected()) {
                int serviceIndex = Arrays.asList(serviceCheckBoxes).indexOf(checkBox);
                totalPrice += servicePrices[serviceIndex];
            }
        }
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }

    // Handle room booking process
    private void bookRoom() {
        double totalPrice = roomBasePrice;  // Start with the base room price
        StringBuilder servicesSelected = new StringBuilder("Selected Services: ");
        for (JCheckBox checkBox : serviceCheckBoxes) {
            if (checkBox.isSelected()) {
                int serviceIndex = Arrays.asList(serviceCheckBoxes).indexOf(checkBox);
                totalPrice += servicePrices[serviceIndex];
                servicesSelected.append(serviceNames[serviceIndex]).append(" ");
            }
        }

        JOptionPane.showMessageDialog(frame, "Room booked successfully!\n" +
                "Room: " + selectedRoom + "\n" +
                servicesSelected.toString() + "\nTotal Price: $" + totalPrice);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoomSelectionApp();
            }
        });
    }
}
