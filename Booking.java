package Hotel;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking {
    private double bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private double totalPrice;
    private String status; // Booking status

    private static final String BOOKINGS_FILE = "bookings.csv";

    public Booking(double bookingId, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.status = "Available";
    }

    public long nightStay() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public void calculateTotalPrice(double pricePerNight) {
        long nights = nightStay();
        this.totalPrice = nights * pricePerNight * numberOfGuests;
    }

    public boolean isRoomAvailable() {
        return status.equals("Available");
    }

    public void bookRoom(double pricePerNight) {
        if (isRoomAvailable()) {
            this.status = "Booked";
            calculateTotalPrice(pricePerNight);
            System.out.println("Room successfully booked. Total Price: " + totalPrice);
            saveBookingToFile();
        } else {
            System.out.println("Room is not available.");
        }
    }

    public void confirmBooking() {
        if (this.status.equals("Booked")) {
            this.status = "Confirmed";
            System.out.println("Booking confirmed.");
            updateBookingInDatabase();
        } else {
            System.out.println("Booking cannot be confirmed.");
        }
    }

    public void cancelBooking() {
        if (this.status.equals("Confirmed")) {
            this.status = "Canceled";
            System.out.println("Booking canceled.");
            updateBookingInDatabase();
        } else {
            System.out.println("Booking cannot be canceled because it is " + this.status);
        }
    }

    public void saveBookingToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE, true))) {
            writer.write(toCSV());
            writer.newLine();
            System.out.println("Booking ID " + bookingId + " saved to database.");
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }

    public void updateBookingInDatabase() {
        try {
            List<String> allBookings = readBookingsFromFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
                for (String booking : allBookings) {
                    if (booking.contains(String.valueOf(bookingId))) {
                        writer.write(toCSV());
                    } else {
                        writer.write(booking);
                    }
                    writer.newLine();
                }
            }
            System.out.println("Booking ID " + bookingId + " updated in database.");
        } catch (IOException e) {
            System.out.println("Error updating booking: " + e.getMessage());
        }
    }

    public void deleteBookingFromDatabase() {
        try {
            List<String> allBookings = readBookingsFromFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE))) {
                for (String booking : allBookings) {
                    if (!booking.contains(String.valueOf(bookingId))) {
                        writer.write(booking);
                        writer.newLine();
                    }
                }
            }
            System.out.println("Booking ID " + bookingId + " deleted from database.");
        } catch (IOException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
    }

    private List<String> readBookingsFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            return reader.lines().toList();
        }
    }

    public String toCSV() {
        return bookingId + "," + checkInDate + "," + checkOutDate + "," + numberOfGuests + "," + totalPrice + "," + status;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Check-In: " + checkInDate +
               ", Check-Out: " + checkOutDate + ", Guests: " + numberOfGuests +
               ", Total Price: " + totalPrice + ", Status: " + status;
    }

    // Getters and setters
    public double getBookingId() {
        return bookingId;
    }

    public void setBookingId(double bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
