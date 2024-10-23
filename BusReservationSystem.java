/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservation;

import java.util.ArrayList;

public class BusReservationSystem {
    private ArrayList<Bus> busList;

    public BusReservationSystem() {
        busList = new ArrayList<>();
    }

    public void addBus(Bus bus) {
        busList.add(bus);
    }

    public void checkAvailability() {
        for (Bus bus : busList) {
            System.out.println("Bus ID: " + bus.getBusID() + ", Type: " + bus.getBusType() +
                               ", Available Seats: " + bus.getAvailableSeats());
        }
    }

    public boolean bookTicket(int busID, int seats) {
        for (Bus bus : busList) {
            if (bus.getBusID() == busID) {
                return bus.bookSeats(seats);  // Attempt to book seats
            }
        }
        return false;  // Bus not found
    }

    public void cancelTicket(int busID, int seats) {
        for (Bus bus : busList) {
            if (bus.getBusID() == busID) {
                bus.cancelSeats(seats);  // Cancel the specified number of seats
                return;
            }
        }
        System.out.println("Bus ID not found!");
    }
}



