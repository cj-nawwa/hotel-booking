public class DatabaseHelper {


   abstract class databaseFunctions {
       abstract void create(); 
       abstract void update();
       abstract void delete();
   }


// Manager class creates a new user and takes a password   
class Manager extends databaseFunctions  {
       private String username;
       private String password;
       private String role;


       public Manager(String username, String password, String role) {
           this.username = username;
           this.password = password;
           this.role = role;
       }


       public Manager() {
           this.username = "username";
           this.password = "xxxx";
           this.role = "guest";
       }


       @Override
       void create() {
           // This is where the database implementation would be.
           System.out.println("The user " + username + " was added as a " + role);
       }
        @Override
       void update() {
           // This is where the database implementation would be.
           System.out.println("User to be updated is " + username);
       }


       @Override
       void delete() {
           // This is where the database implementation would be.
           System.out.println("The User to be deleted is " + username);
       }
   }
// RoomManager creates new rooms, including their ID, type, and price.
   class RoomManager extends databaseFunctions {
       private String roomId;
       private String type;
       private double price;


       public RoomManager(String roomId, String type, double price) {
           this.roomId = roomId;
           this.type = type;
           this.price = price;
       }


       @Override
       void create() {
           // This is where the database implementation would be.
           System.out.println("The room to be created has ID " + roomId + ", Type " + type + ", and Price " + price);
       }


       @Override
       void update() {
           // This is where the database implementation would be.
           System.out.println("The room to be updated has ID " + roomId);
       }


       @Override
       void delete() {
           // This is where the database implementation would be.
           System.out.println("The room to be deleted has ID " + roomId);
       }
   }
// BookingManager creates a new booking for a user, with the room and dates.
   class BookingManager extends databaseFunctions {
       private String bookingId;
       private String userName;
       private String roomId;
       private String startDate;
       private String endDate;


       public BookingManager(String bookingId, String userName, String roomId, String startDate, String endDate) {
           this.bookingId = bookingId;
           this.userName = userName;
           this.roomId = roomId;
           this.startDate = startDate;
           this.endDate = endDate;
       }


       @Override
       void create() {
           // This is where the database implementation would be.
           System.out.println("The booking to be created has ID " + bookingId + ", User " + userName + ", and Room ID " + roomId);
       }


       @Override
       void update() {
           // This is where the database implementation would be.
           System.out.println("The booking to be updated has ID " + bookingId);
       }


       @Override
       void delete() {
           // This is where the database implementation would be.
           System.out.println("The booking to be deleted has ID " + bookingId);
       }
   }
}








