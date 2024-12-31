import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard {

    private JFrame frame;

    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // User Management Button
        JButton userManagementButton = new JButton("User Management");
        userManagementButton.setFont(new Font("Arial", Font.PLAIN, 16));
        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to User Management Page
                JOptionPane.showMessageDialog(frame, "Redirecting to User Management Page.");
                // Add logic to connect to User Management
            }
        });
        buttonPanel.add(userManagementButton);

        // Room Management Button
        JButton roomManagementButton = new JButton("Room Management");
        roomManagementButton.setFont(new Font("Arial", Font.PLAIN, 16));
        roomManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to Room Management Page
                JOptionPane.showMessageDialog(frame, "Redirecting to Room Management Page.");
                // Add logic to connect to Room Management
            }
        });
        buttonPanel.add(roomManagementButton);

        // Booking Management Button
        JButton bookingManagementButton = new JButton("Booking Management");
        bookingManagementButton.setFont(new Font("Arial", Font.PLAIN, 16));
        bookingManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to Booking Management Page
                JOptionPane.showMessageDialog(frame, "Redirecting to Booking Management Page.");
                // Add logic to connect to Booking Management
            }
        });
        buttonPanel.add(bookingManagementButton);

        // Center the button panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(buttonPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}



