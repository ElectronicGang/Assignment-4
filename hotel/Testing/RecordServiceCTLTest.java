/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kalpa
 StudentID: 11634268
 */
public class RecordServiceCTLTest {
    
    Booking booking;//instance for find bookings
    
    public RecordServiceCTLTest() {
    }
 
    /**
     * Test of roomNumberEntered method, of class RecordServiceCTL.
     */
    @Test
    public void testRoomNumberEntered() {
        System.out.println("roomNumberEntered");
        Long confirmationNumber = 0L;
        Hotel hotel = new Hotel();// create hotel instance to make booking
        Room room = new Room(201,RoomType.SINGLE); //create a room instance for booking
        hotel.addRoom(RoomType.SINGLE, 201);
        Guest guest =hotel.registerGuest("Sandy", "186 Wright st", 0451234563);// create a guest object
        CreditCard creditCard = new CreditCard(CreditCardType.MASTERCARD,4217,415); // create a credit card object
        hotel.findAvailableRoom(RoomType.SINGLE, new Date(2018, 11, 05), 7);
        confirmationNumber = hotel.book(room, guest, new Date(2018, 11, 05), 7, 1, creditCard); // generate a confirmation number through booking
        hotel.checkin(confirmationNumber);// execute guest check in to room.
        int roomId = 201;
      
        CheckoutCTL instance = new CheckoutCTL(hotel);
        instance.run();
       instance.roomIdEntered(roomId);
        instance.chargesAccepted(true);
        instance.creditDetailsEntered(CreditCardType.VISA, 4217, 415);
        
        RecordServiceCTL recordService = new RecordServiceCTL(hotel); // create an instance of record service to add service
        recordService.roomNumberEntered(roomId);// pass room id to find booking
        recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, 150.0);// add extra service for the booking
        
        booking = hotel.findActiveBookingByRoomId(roomId);
        boolean roomAvailable = true;
        if(booking != null){
            roomAvailable = false;
        }
        assertTrue("Room Status", roomAvailable);
    }

    

  
    
}
