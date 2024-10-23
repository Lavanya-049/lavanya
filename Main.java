/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservation;

/**
 *
 * @author lavan
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BusReservationSystem system = new BusReservationSystem();
        
        system.addBus(new Bus(101, 40, "AC"));
        system.addBus(new Bus(102, 50, "Non-AC"));

        Scanner o = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n---- Bus Reservation System ----");
            System.out.println("1. Check Availability");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Cancel a Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = o.nextInt();

            switch (choice) {
                case 1:
                    system.checkAvailability();
                    break;

                case 2:
                    System.out.print("Enter Bus ID: ");
                    int busID = o.nextInt();
                    System.out.print("Enter number of seats to book: ");
                    int seats = o.nextInt();
                    if (system.bookTicket(busID, seats)) {
                        System.out.println("Seats booked successfully!");
                    } else {
                        System.out.println("Failed to book seats. Not enough availability.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Bus ID: ");
                    busID = o.nextInt();
                    System.out.print("Enter number of seats to cancel: ");
                    seats = o.nextInt();
                    system.cancelTicket(busID, seats);
                    System.out.println("Cancellation successful!");
                    break;

                case 4:
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        o.close();
    }
}


