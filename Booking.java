import java.time.LocalDate;
import java.time.temporal.ChronoUnit; /* apparently we can use this to call for time like month and such
that when we call it in the main class we can set the date ,month ... by creating an instance.*/

public class Booking {// we will inherit from the class of our need

private String bookingId; // basically booking id
/* there are probably 2 attributes that we will probably use based on 
 * the implementation of my group members 
 * that is the class room and user 
 */
private String roomID;
private LocalDate checkInDate;/* apparently there is local date by java using java.time package 
*so we will use this to make it easier  
checked in by the user*/

private LocalDate checkOutDate;// same here time package checkout by the user 
private int numberOfGuests; // total number of guests 
private double totalPrice;
private String status;
//booking status basically confirmed or not

private Room[] rooms;
	public Booking(String bookingId, String roomID,LocalDate checkInDate,LocalDate checkOutDate,
			int numberOfGuests,double totalPrice, String status ) {

		this.bookingId = bookingId;
		this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
        this.status = status;
		}
	// a method to set dates of stay by using the long data type  
	public long nightStay() {
		return ChronoUnit.DAYS.between(checkOutDate, checkInDate);
	}
	public void bookdetail() {// just to tell the details of the booking ! attributes may be added 
		System.out.println("Booking ID: " + bookingId +
	               "\nCheck-in: " + checkInDate +
	               "\nCheck-out: " + checkOutDate +
	               "\nNumber of Guests: " + numberOfGuests +
	               "\nTotal Price: " + totalPrice +
	               "\nStatus: " + status);
	}
	public void bookRoom() {
		rooms = new Room[]{
				new Room("007", "VIP",99.9,false,3),
				new Room("202", "Economic", 29.9,false, 1),
				new Room("303", "Luxury",59.9, false, 2)
		};
	    switch (this.status) {
	        case "Available":
	            this.status = "Booked"; // status is now booked
	            break;
	        case "Booked":
	            break;
	        case "Canceled":
	            break;
	        default:
	            break;
	    }
	}
	public void cancelBooking() {
	    switch (this.status) {
	        case "Confirmed":
	            this.status = "Canceled"; //  status is now canceled if user want to cancel  
	            System.out.println("Booking canceled.");
	            break;
	        case "Canceled":
	            System.out.println("Booking is canceled.");
	            break;
	        default:
	            System.out.println("Booking cannot be canceled because it is " + this.status);
	            break;
	    }
	    
	}
	
	// set and get methods , still not finished some attributed might get removed for now only 
	public void setbookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getbookingId() {
		return bookingId;
	}
	public void setRoomID(String roomID){
		this. roomID = roomID;
	}
	public String getRoomID(){
		return roomID;
	}
	public void setstatus(String status) {
		this.status= status;
	}
	public String getstatus() {
		return status;
	}
	public void setcheckInDate(LocalDate checkInDate) {
		this.checkInDate= checkInDate;
	}
	public LocalDate getcheckInDate() {
		return checkInDate;
	}
	public void setcheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate= checkOutDate;
	}
	public LocalDate getcheckOutDate() {
		return checkOutDate;
	}
	public void settotalPrice(double totalPrice) {
		this. totalPrice= totalPrice;
	}
	public double gettotalPrice() {
		return  totalPrice;
	}
	public void setnumberOfGuests(int numberOfGuests) {
		this.numberOfGuests=numberOfGuests;
	}
	public int getnumberOfGuests() {
		return numberOfGuests;
	}
}