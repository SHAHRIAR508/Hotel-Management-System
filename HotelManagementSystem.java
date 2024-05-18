import javax.swing.JOptionPane;

public class HotelManagementSystem {
    public void displayRoomDetails() {
        // Implement logic to display room details
        JOptionPane.showMessageDialog(null, "Displaying Room Details");
    }
 
    public void displayRoomAvailability() {
        System.out.println("\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n");
                            //ch2 = sc.nextInt();
                            // Hotel.features(ch2);
        JOptionPane.showMessageDialog(null, "Displaying Room Availability");
    }

    public void bookRoom() {
        // Implement logic for booking room
        JOptionPane.showMessageDialog(null, "Booking Room");
    }

    public void orderFood() {
        // Implement logic for ordering food
        JOptionPane.showMessageDialog(null, "Ordering Food");
    }

    public void checkout() {
        // Implement logic for checkout
        JOptionPane.showMessageDialog(null, "Checking Out");
    }
}
