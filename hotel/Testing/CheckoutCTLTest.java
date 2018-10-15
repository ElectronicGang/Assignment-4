

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Kalpa StudentID: 11634268
 */
public class CheckoutCTLTest {

    public CheckoutCTLTest() {
    }

    @Test
    public void testRoomIdEntered() {
        Long confirmationNumber = 0L;
        Hotel hotel = new Hotel();// create hotel instance to make booking
        Room room = new Room(609, RoomType.SINGLE); //create a room instance for booking
        hotel.addRoom(RoomType.SINGLE, 609);
        System.out.println("roomIdEntered");

        Guest guest = hotel.registerGuest("Recep", "3/139 Cairns Road", 0451234563);// create a guest object
        CreditCard creditCard = new CreditCard(CreditCardType.MASTERCARD, 40179545, 275); // create a credit card object
        hotel.findAvailableRoom(RoomType.SINGLE, new Date(2018, 12, 06), 3);
        confirmationNumber = hotel.book(room, guest, new Date(2018, 12, 06), 3, 1, creditCard); // generate a confirmation number for that particular booking
        hotel.checkin(confirmationNumber);// execute guest check in to room.
        int roomId = 609;
        double costOfBooking = 100 * 3;

        CheckoutCTL instance = new CheckoutCTL(hotel);
        instance.run();
        instance.roomIdEntered(roomId);

        instance.chargesAccepted(true);
        instance.creditDetailsEntered(CreditCardType.VISA, 40179545, 275);
        assertEquals("Cost", costOfBooking, instance.getTotal());
        System.out.println(costOfBooking);
    }

}
