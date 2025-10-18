import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void freeRoom() {
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - $" + price + " per night - " +
                (isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    String customerName;
    int roomNumber;
    int nights;
    double totalCost;

    public Reservation(String customerName, int roomNumber, int nights, double totalCost) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Reservation: " + customerName + " - Room " + roomNumber +
                " for " + nights + " night(s) - Total Cost: $" + totalCost;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();

        System.out.println("Welcome to the Hotel Reservation System!");
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. View All Rooms");
            System.out.println("2. Search Available Rooms");
            System.out.println("3. Make a Reservation");
            System.out.println("4. View All Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewAllRooms();
                case 2 -> searchAvailableRooms();
                case 3 -> makeReservation();
                case 4 -> viewReservations();
                case 5 -> System.out.println("Thank you for using the Hotel Reservation System!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Initialize rooms
    public static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 250));
        rooms.add(new Room(104, "Single", 100));
        rooms.add(new Room(105, "Double", 150));
    }

    // View all rooms
    public static void viewAllRooms() {
        System.out.println("\nAll Rooms:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    // Search for available rooms
    public static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are currently available.");
        }
    }

    // Make a reservation
    public static void makeReservation() {
        System.out.print("\nEnter your name: ");
        scanner.nextLine(); // Consume newline
        String customerName = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        for (Room room : rooms) {
            if (room.isAvailable && room.type.equalsIgnoreCase(roomType)) {
                double totalCost = room.price * nights;
                room.bookRoom();
                Reservation reservation = new Reservation(customerName, room.roomNumber, nights, totalCost);
                reservations.add(reservation);

                System.out.println("Reservation successful! " + reservation);
                return;
            }
        }

        System.out.println("Sorry, no available rooms of the requested type.");
    }

    // View all reservations
    public static void viewReservations() {
        System.out.println("\nAll Reservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations have been made.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

