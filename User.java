public class User {
    // Attributes
    private String username;
    private String password;
    private String role; // Can be "customer" or "admin"

    // Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        setRole(role); // Validates and sets the role
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

    public void setRole(String role) {
        if (role.equalsIgnoreCase("Guest") || role.equalsIgnoreCase("Admin")) {
            this.role = role;
        } else {
            System.out.println("Invalid role. Role must be 'customer' or 'admin'.");
            // throw new IllegalArgumentException("Invalid role. Role must be 'customer' or 'admin'.");
        }
    }

    // Method to register a new user
    public void register(String username, String password, String role) {
        this.username = username;
        this.password = password;
        setRole(role); // Validates and sets the role
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
