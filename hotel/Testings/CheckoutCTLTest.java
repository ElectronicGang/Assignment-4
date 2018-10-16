/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package checkout;

import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Dilan
 */
public class CheckoutCTLTest {
    
    public CheckoutCTLTest() {
    }
    
   @Test
    public void testRoomIdEntered() {
        System.out.println("roomIdEntered");
        Long confirmationNumber = 0L;
        Hotel hotel = new Hotel();// create hotel instance to make booking
        Room room = new Room(201,RoomType.SINGLE); //create a room instance for booking
        hotel.addRoom(RoomType.SINGLE, 201);
        Guest guest =hotel.registerGuest("Sandy", "186 Wright st", 0451234563);// create a guest object
        CreditCard creditCard = new CreditCard(CreditCardType.MASTERCARD,4217,415); // create a credit card object
        hotel.findAvailableRoom(RoomType.SINGLE, new Date(2018, 11, 05), 7);
        double costOfBooking = 100*7;
        confirmationNumber = hotel.book(room, guest, new Date(2018, 11, 05), 7, 1, creditCard,costOfBooking); // generate a confirmation number through booking
        hotel.checkin(confirmationNumber);// execute guest check in to room.
        int roomId = 201;
        
        CheckoutCTL instance = new CheckoutCTL(hotel);
        instance.run();
       instance.roomIdEntered(roomId);
        instance.chargesAccepted(true);
        instance.creditDetailsEntered(CreditCardType.VISA, 4217, 415);
        
         assertEquals(costOfBooking, instance.getTotal(), 0.0);
    }
    
    
    
    
}
