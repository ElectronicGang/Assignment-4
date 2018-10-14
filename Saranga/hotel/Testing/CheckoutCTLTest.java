
package hotel.checkout;

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
        Hotel hotelTest = new Hotel();// create hotel instance to make booking
        Room room = new Room(101,RoomType.DOUBLE); //create a room instance for booking
        hotelTest.addRoom(RoomType.DOUBLE, 101);
        Guest guest =hotelTest.registerGuest("Nimal", "123 Elisabath st", 0451234563);// create a guest object
        CreditCard creditCard = new CreditCard(CreditCardType.VISA,6543,987); // create a credit card object
        hotelTest.findAvailableRoom(RoomType.DOUBLE, new Date(2018, 12, 05), 7);
        confirmationNumber = hotelTest.book(room, guest, new Date(2018, 12, 05), 7, 1, creditCard, 100); // generate a confirmation number through booking
        hotelTest.checkin(confirmationNumber);// execute guest check in to room.
        int roomId = 101;
        double costOfBooking = 100*7;
        
        CheckoutCTL instance = new CheckoutCTL(hotelTest);
        instance.run();
       instance.roomIdEntered(roomId);
        instance.chargesAccepted(true);
        instance.creditDetailsEntered(CreditCardType.VISA, 6543, 987);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("Cost", costOfBooking, instance.getTotal());
       System.out.println(costOfBooking);
    }    
    
    
}