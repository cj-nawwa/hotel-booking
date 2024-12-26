

### Proposed Modules and Responsibilities
1. Authentication System:  
   - Beginner-friendly class: A User class with attributes like username, password, and role (customer or admin).
   - Create basic methods for login and registration.

2. Room Management:  
   - Admin-focused functionalities:
     - A Room class with attributes for room type, availability, and fixed price.
     - Methods for updating availability.
   - Subclassing could handle different room types.

3. Booking System:  
   - A Booking class with attributes for the user, room type, and booking dates.
   - Methods to check availability using a calendar model.
   - Allow cancellation or rescheduling.

4. Calendar View:  
   - A simple table view showing room availability for selected dates.
   - Interactive selection for customers to pick dates and room type.

5. Graphical User Interface (GUI):  
   - Use Java Swing for simplicity.
   - Design separate panels:
     - Login/Registration Page.
     - Customer Dashboard (showing room availability and booking options).
     - Admin Dashboard (managing room availability).

6. Database Integration:  
   - A small Java database to store users, rooms, and bookings.
   - Use SQLlite or H2 for simplicity.
     
### Classes for the Project
1. User Class  
   - Attributes: username, password, role (customer/admin).  
   - Methods:  
     - register(): Registers a new user in the system.  
     - login(): Verifies username and password for login.  
     - getRole(): Returns user role.  
   - Team Assignment: Beginner-friendly.

2. Room Class  
   - Attributes: roomID, type (single, double, suite), price, availability (boolean or list of dates).  
   - Methods:  
     - getDetails(): Returns room type and price.  
     - setAvailability(): Updates room availability.  
     - isAvailable(date): Checks if the room is available for a specific date.  
   - Team Assignment: Intermediate level.  

3. Booking Class  
   - Attributes: bookingID, userID, roomType, startDate, endDate.  
   - Methods:  
     - createBooking(): Makes a booking for a user if the room is available.  
     - cancelBooking(): Cancels an existing booking.  
     - modifyBooking(): Updates booking dates.  
   - Team Assignment: Advanced beginner or intermediate.

4. Calendar Class  
   - Attributes: dateMatrix (e.g., a 2D array where rows = room types, columns = dates).  
   - Methods:  
     - showAvailability(): Displays room availability in a calendar format.  
     - selectDates(): Allows users to select dates for booking.  
   - Team Assignment: Advanced beginner or intermediate.

5. AdminDashboard Class  
   - Attributes: adminID, managedRooms (list of Room objects).  
   - Methods:  
     - viewRooms(): Shows current room availability and prices.  
     - updateRoomAvailability(): Updates availability of specific rooms.  
   - Team Assignment: Beginner-friendly.

6. CustomerDashboard Class  
   - Attributes: userID, bookedRooms (list of bookings).  
   - Methods:  
     - viewAvailableRooms(): Displays available rooms based on selected dates.  
     - bookRoom(): Calls createBooking() from Booking class.  
     - cancelBooking(): Calls cancelBooking() from Booking class.  
   - Team Assignment: Beginner-friendly.

7. DatabaseHelper Class  
   - Attributes: Connection to database.  
   - Methods:  
     - addUser(): Adds new user to the database.  
     - addRoom(): Adds rooms to the database during initialization.  
     - addBooking(): Saves booking details in the database.  
     - query(): Executes SQL queries for retrieving data.  
   - Team Assignment: Intermediate/Advanced.

8. Main Application Class  
   - Purpose: Acts as the entry point for the application.  
   - Methods:  
     - Initializes GUI components and links different classes.  
     - Sets up the database during the first run.  
   - Team Assignment: Intermediate/Advanced.

---

### Suggested Team Division
- Member 1: User Class  
- Member 2: Room Class  
- Member 3: Booking Class  
- Member 4: Calendar Class  
- Member 5: AdminDashboard Class  
- Member 6: CustomerDashboard Class  
- Member 7: DatabaseHelper Class  
- Member 8: Main Application Class  

Collaboration Note:  
- Pair beginners with experienced members for more complex classes like Booking, Calendar, and DatabaseHelper.  
- Use version control (e.g., Git) to integrate work effectively.  

Would you like a sample class template to start with?
