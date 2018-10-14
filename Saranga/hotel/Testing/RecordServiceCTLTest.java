/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hotel.service;

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

public class RecordServiceCTLTest {
    
    Booking booking;//instance for find bookings
    
    public RecordServiceCTLTest() {
    }
 
    /**
     * Test of roomNumberEntered method, of class RecordServiceCTL.
     */
    @Test
    public void testRoomNumberEntered() {
        System.out.println("room id entered");
        Long confirmationNumber = 0L;
        Hotel hotel = new Hotel();// create hotel instance to make booking
        Room room = new Room(102,RoomType.DOUBLE); //create a room instance for booking
        hotel.addRoom(RoomType.DOUBLE, 201);
        Guest guest =hotel.registerGuest("Nimal", "200 Elisabath st", 045163);// create a guest object
        CreditCard creditCard = new CreditCard(CreditCardType.VISA,4878,315); // create a credit card object
        hotel.findAvailableRoom(RoomType.DOUBLE, new Date(2018, 10, 05), 7);
        confirmationNumber = hotel.book(room, guest, new Date(2018, 10, 05), 7, 4, creditCard); // generate a confirmation number through booking
        hotel.checkin(confirmationNumber);// execute guest check in to room.
        int roomId = 201;
      
        CheckoutCTL instance = new CheckoutCTL(hotel);
        instance.run();
       instance.roomIdEntered(roomId);
        instance.chargesAccepted(true);
        instance.creditDetailsEntered(CreditCardType.VISA, 4878, 315);
        
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
