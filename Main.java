//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
//public class Main {
//    public static void main(String[] args) {
//        new LoginFrame();
//    }
//}
//
//class LoginFrame extends JFrame implements ActionListener {
//    JTextField usernameField;
//    JPasswordField passwordField;
//    JLabel messageLabel;
//
//    public LoginFrame() {
//        // Frame settings
//        setTitle("Login Page");
//        setSize(350, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(4, 1));
//
//        // Username
//        JPanel userPanel = new JPanel();
//        userPanel.add(new JLabel("Username:"));
//        usernameField = new JTextField(15);
//        userPanel.add(usernameField);
//        add(userPanel);
//
//        // Password
//        JPanel passPanel = new JPanel();
//        passPanel.add(new JLabel("Password:"));
//        passwordField = new JPasswordField(15);
//        passPanel.add(passwordField);
//        add(passPanel);
//
//        // Message Label
//        messageLabel = new JLabel("", JLabel.CENTER);
//        add(messageLabel);
//
//        // Login Button
//        JButton loginButton = new JButton("Login");
//        loginButton.addActionListener(this);
//        add(loginButton);
//
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        if (validateLogin(username, password)) {
//            // close current window
//            this.dispose();
//            if (username.equals("admin")){
//                new Dashboard("admin","Guest");
//            }
//        } else {
//            messageLabel.setText("Invalid Username or Password");
//            messageLabel.setForeground(Color.RED);
//        }
//    }
//
//    // Simple validation method
//    private boolean validateLogin(String username, String password) {
//        return username.equals("admin") && password.equals("password123");
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize with some default users
        users.add(new User("admin", "admin123", "Admin"));
        users.add(new User("Guest", "guest123", "Guest"));

        new LoginFrame();
    }

    // Login Frame
    static class LoginFrame extends JFrame implements ActionListener {
        JTextField usernameField;
        JPasswordField passwordField;
        JLabel messageLabel;

        public LoginFrame() {
            setTitle("Login Page");
            setSize(400, 250);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 1));

            // Username Field
            JPanel userPanel = new JPanel();
            userPanel.add(new JLabel("Username:"));
            usernameField = new JTextField(15);
            userPanel.add(usernameField);
            add(userPanel);

            // Password Field
            JPanel passPanel = new JPanel();
            passPanel.add(new JLabel("Password:"));
            passwordField = new JPasswordField(15);
            passPanel.add(passwordField);
            add(passPanel);

            // Message Label
            messageLabel = new JLabel("", JLabel.CENTER);
            add(messageLabel);

            // Login and Register Buttons
            JButton loginButton = new JButton("Login");
            JButton registerButton = new JButton("Register");
            loginButton.addActionListener(this);
            registerButton.addActionListener(e -> openRegistrationDialog());
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(loginButton);
            buttonPanel.add(registerButton);
            add(buttonPanel);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = validateLogin(username, password);
            if (user != null) {
                this.dispose();
                new Dashboard(user.getUsername(), user.getRole());
            } else {
                messageLabel.setText("Invalid Credentials");
                messageLabel.setForeground(Color.RED);
            }
        }

        // Validate Login
        private User validateLogin(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            return null;
        }

        // Registration Dialog
        private void openRegistrationDialog() {
            JDialog registerDialog = new JDialog(this, "Register New User", true);
            registerDialog.setSize(350, 300);
            registerDialog.setLayout(new GridLayout(5, 2));
            registerDialog.setLocationRelativeTo(this);

            JTextField regUsernameField = new JTextField();
            JTextField regEmailField = new JTextField();
            JPasswordField regPasswordField = new JPasswordField();

            // Form Fields
            registerDialog.add(new JLabel("Username:"));
            registerDialog.add(regUsernameField);
            registerDialog.add(new JLabel("Email:"));
            registerDialog.add(regEmailField);
            registerDialog.add(new JLabel("Password:"));
            registerDialog.add(regPasswordField);

            // Confirm Button
            JButton confirmButton = new JButton("Register");
            confirmButton.addActionListener(e -> {
                String username = regUsernameField.getText();
                String email = regEmailField.getText();
                String password = new String(regPasswordField.getPassword());

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerDialog, "All fields are required.");
                } else {
                    users.add(new User(username, password, "Guest"));
                    JOptionPane.showMessageDialog(registerDialog, "User Registered Successfully!");
                    registerDialog.dispose();
                }
            });

            registerDialog.add(new JLabel());
            registerDialog.add(confirmButton);
            registerDialog.setVisible(true);
        }
    }
}