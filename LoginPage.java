import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPage {

    private static DatabaseHelper databaseHelper = new DatabaseHelper(); // Use DatabaseHelper

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Login and Register Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Show the login/register screen
        showLoginRegisterScreen(frame);

        frame.setVisible(true);
    }

    private static void showLoginRegisterScreen(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(new JLabel("Welcome to the System", SwingConstants.CENTER));
        panel.add(loginButton);
        panel.add(registerButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener(e -> showLoginScreen(frame));
        registerButton.addActionListener(e -> showRegisterScreen(frame));
    }

    private static void showLoginScreen(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Authenticate the user using DatabaseHelper
            List<String> users = databaseHelper.new Manager("", "", "").readAll();
            boolean found = false;

            for (String user : users) {
                String[] userData = user.split(",");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void showRegisterScreen(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(registerButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Check for duplicate users using DatabaseHelper
            List<String> users = databaseHelper.new Manager("", "", "").readAll();
            for (String user : users) {
                String[] userData = user.split(",");
                if (userData[0].equals(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Register the user using DatabaseHelper
            DatabaseHelper.Manager manager = databaseHelper.new Manager(username, password, "Guest");
            manager.create();
            JOptionPane.showMessageDialog(frame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showLoginRegisterScreen(frame);
        });
    }
}





public class User {
    // Attributes
    private String username;
    private String password;
    private String role; // Can be "customer" or "admin"

    // Admin credentials
    private static String adminUserName = "admin";
    private static String adminUserPassword = "admin";

    // Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        setRole(); // Automatically sets the role based on credentials
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole() {
        // Check if the credentials match the admin
        if (this.username.equals(adminUserName) && this.password.equals(adminUserPassword)) {
            this.role = "Admin";
        } else {
            this.role = "Guest";
        }
    }

    // Method to register a new user
    public void register(String username, String password) {
        this.username = username;
        this.password = password;
        setRole(); // Automatically sets the role based on credentials
        System.out.println("User registered successfully!");
    }

    // Method to verify login credentials
    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
}




