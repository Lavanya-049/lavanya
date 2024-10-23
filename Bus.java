/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservation;

/**
 *
 * @author lavan
 */
public class Bus {
    private int busID;
    private int capacity;
    private String busType;  // "AC" or "Non-AC"
    private int availableSeats;

    public Bus(int busID, int capacity, String busType) {
        this.busID = busID;
        this.capacity = capacity;
        this.busType = busType;
        this.availableSeats = capacity;  // Initially, all seats are available
    }

    public int getBusID() {
        return busID;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getBusType() {
        return busType;
    }

    public boolean bookSeats(int seats) {
        if (availableSeats >= seats) {
            availableSeats -= seats;  // Reduce available seats
            return true;  // Booking successful
        } else {
            return false;  // Not enough seats
        }
    }

    public void cancelSeats(int seats) {
        availableSeats += seats;  // Increase available seats
        if (availableSeats > capacity) {
            availableSeats = capacity;  // Ensure it doesn't exceed the capacity
        }
    }
}


