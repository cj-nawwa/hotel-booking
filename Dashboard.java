import java.time.LocalDate;

public class Dashboard extends Booking {
    private String customerName; // Name of the customer
    private String customerEmail; // Email ID of the customer

    // Constructor to initialize Dashboard class
    public Dashboard(double bookingId, LocalDate checkInDate, LocalDate checkOutDate,
                     int numberOfGuests, double totalPrice, String status,
                     String customerName, String customerEmail) {
        super(bookingId, checkInDate, checkOutDate, numberOfGuests, totalPrice, status);
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    // Method to display dashboard details
    public void showDashboard() {
        System.out.println("Customer Dashboard");
        System.out.println("------------------");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Email: " + customerEmail);
        bookdetail(); // Calls the method from the parent class to display booking details
    }

    // Getters and setters for customerName and customerEmail
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
