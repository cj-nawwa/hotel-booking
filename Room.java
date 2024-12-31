import java.util.*;

public class RoomManager {

    private RoomDataHandler dataHandler;

    public RoomManager(RoomDataHandler handler) {
        this.dataHandler = handler;
    }

    public List<String[]> getAvailableRooms() {
        List<String[]> allRooms = dataHandler.fetchAllRooms();
        List<String[]> availableRooms = new ArrayList<>();

        for (String[] room : allRooms) {
            boolean isAvailable = Boolean.parseBoolean(room[4]);
            if (isAvailable) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public static void main(String[] args) {
        // Example usage with a mock handler
        RoomDataHandler mockHandler = new RoomDataHandler() {
            @Override
            public List<String[]> fetchAllRooms() {
                // Mock data for demonstration
                List<String[]> rooms = new ArrayList<>();
                rooms.add(new String[]{"101", "Single", "100.0", "1", "true"});
                rooms.add(new String[]{"102", "Double", "150.0", "2", "false"});
                rooms.add(new String[]{"103", "Suite", "300.0", "3", "true"});
                return rooms;
            }
        };

        RoomManager manager = new RoomManager(mockHandler);
        List<String[]> availableRooms = manager.getAvailableRooms();

        for (String[] room : availableRooms) {
            System.out.println("Available Room: " + Arrays.toString(room));
        }
    }
}

interface RoomDataHandler {
    List<String[]> fetchAllRooms();
}

/*
Single Room
One bed for a single guest.
Price: $70 per night.
Double Room
One double bed or two single beds for two guests.
Price: $120 per night.
Deluxe Room
Upgraded decor and amenities for two guests.
Price: $180 per night.
Family Room
Larger room with multiple beds for families.
Price: $250 per night.
Junior Suite
Spacious room with a sitting area for added comfort.
Price: $300 per night.
Executive Suite
Separate living and sleeping areas with luxury features.
Price: $500 per night.
Accessible Room
Designed for guests with disabilities, featuring accessibility amenities.
Price: $100 per night.
Economy Room
Basic room with minimal amenities for budget travelers.
Price: $50 per night.
*/
