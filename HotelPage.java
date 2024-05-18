

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelPage extends JFrame implements ActionListener {
    HotelManagementSystem hotelSystem = new HotelManagementSystem();

    JButton btnBookingDetails, btnDisplayRoomAvailability, btnBook, btnOrderFood, btnCheckout, btnExit;

    Font font = new Font("Cambria", Font.PLAIN, 20);

    public HotelPage() {
        super("Hotel Page");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // Center the frame on the screen
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background Image
        ImageIcon imageIcon = new ImageIcon("resources/bgg.png");
        Image backgroundImage = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 800, 600);
        this.add(background);

        btnBookingDetails = new JButton("Booking Details"); // Changed button text
        btnBookingDetails.setBounds(200, 50, 300, 40); // Adjusted button size and position
        btnBookingDetails.setFont(font);
        btnBookingDetails.addActionListener(this);
        background.add(btnBookingDetails);


        btnDisplayRoomAvailability = new JButton("RoomDetails");
        btnDisplayRoomAvailability.setBounds(200, 110, 300, 40); // Adjusted button size and position
        btnDisplayRoomAvailability.setFont(font);
        btnDisplayRoomAvailability.addActionListener(this);
        background.add(btnDisplayRoomAvailability);

        btnBook = new JButton("Book Room");
        btnBook.setBounds(200, 170, 300, 40); // Adjusted button size and position
        btnBook.setFont(font);
        btnBook.addActionListener(e -> hotelSystem.bookRoom());
        background.add(btnBook);

        btnOrderFood = new JButton("Order Food");
        btnOrderFood.setBounds(200, 230, 300, 40); // Adjusted button size and position
        btnOrderFood.setFont(font);
        btnOrderFood.addActionListener(e -> hotelSystem.orderFood());
        background.add(btnOrderFood);

        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(200, 290, 300, 40); // Adjusted button size and position
        btnCheckout.setFont(font);
        btnCheckout.addActionListener(this);
        background.add(btnCheckout);

        btnExit = new JButton("Exit");
        btnExit.setBounds(200, 350, 300, 40); // Adjusted button size and position
        btnExit.setFont(font);
        btnExit.addActionListener(e -> System.exit(0)); // Exit the application
        background.add(btnExit);

        this.setVisible(true);
    }

   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnBookingDetails) {
        new RoomManagementPage();
        this.setVisible(false);
    } else if (e.getSource() == btnDisplayRoomAvailability) {
        new RoomDetailsPage();
        this.setVisible(false);
    } else if (e.getSource() == btnCheckout) {
        new CheckoutPage();
        this.setVisible(false);
    }
}

public static void main(String[] args) {
    new HotelPage();
}

}
