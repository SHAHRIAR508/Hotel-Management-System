

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CheckoutPage extends JFrame implements ActionListener {
    Font font = new Font("Lucida Sans Unicode", Font.PLAIN, 16);
    Font font2 = new Font("Bookman Old Style", Font.BOLD, 40);
    JPanel panel;
    JLabel label;
    JTextField roomNumberField;
    JButton checkout, back;
    DefaultTableModel model;
    JTable table;

    public CheckoutPage() {
        initializeFrame();
        createTable();
        readFromFile(); 
		
		
		

        this.setVisible(true);
    }
	
	
	
	

    public void initializeFrame() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Center the frame on the screen
        this.setLayout(null);

        // Creating a Panel Container
        panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        this.add(panel);

       label = new JLabel("Checkout Room");
       label.setBounds(200, 10, 400, 50); // Adjusted width to 400 to accommodate the full text
       label.setFont(font2);
       panel.add(label);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setFont(font);
        roomNumberLabel.setBounds(50, 100, 150, 30);
        panel.add(roomNumberLabel);

        // TextFields
        roomNumberField = new JTextField();
        roomNumberField.setFont(font);
        roomNumberField.setBounds(200, 100, 200, 30);
        panel.add(roomNumberField);

        // Creating Buttons
        checkout = new JButton("Checkout");
checkout.setFont(font);
checkout.setBounds(200, 150, 150, 40); // Adjusted width to accommodate full text
checkout.setBackground(new Color(36, 214, 42)); // Green color
checkout.setForeground(Color.WHITE); // Text color
checkout.addActionListener(this);
panel.add(checkout);

back = new JButton("Back");
back.setBounds(370, 150, 100, 40); // Adjusted position for proper spacing
back.setFont(font);
back.setBackground(new Color(214, 36, 36)); // Red color
back.setForeground(Color.WHITE); // Text color
back.addActionListener(this);
panel.add(back);

    }

    public void createTable() {
        // Create Model For Table
        model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Room Type");
        model.addColumn("Status");
        model.addColumn("Price");

        // Create Table with model
        table = new JTable(model);
        table.setFont(font);
        table.setBackground(new Color(146, 211, 247));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(247, 146, 146));
        table.getTableHeader().setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 220, 700, 300);
        panel.add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkout) {
            // Handle checkout
            String roomNumber = roomNumberField.getText();
            if (checkoutRoom(roomNumber)) {
                JOptionPane.showMessageDialog(this, "Room " + roomNumber + " checked out successfully!");
                model.setRowCount(0); // Clear table
                readFromFile(); // Refresh table data
            } else {
                JOptionPane.showMessageDialog(this, "Room " + roomNumber + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            roomNumberField.setText(""); // Clear text field after checkout
        } else if (e.getSource() == back) {
            // Handle going back to previous page
            this.dispose();
            // Assuming HotelPage is another class that you've implemented
            new HotelPage();
        }
    }

public boolean checkoutRoom(String roomNumber) {
    try (BufferedReader br = new BufferedReader(new FileReader("./availableRooms.txt"));
         BufferedWriter bw = new BufferedWriter(new FileWriter("./temp.txt"))) {
        String line;
        boolean roomFound = false;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            if (data[0].equals(roomNumber)) {
                roomFound = true;
                data[2] = "Available"; // Change status to "Available"
                line = String.join(";", data);
            }
            bw.write(line + System.lineSeparator());
        }
        if (!roomFound) return false;
    } catch (IOException e) {
        System.err.println("Error reading/writing file: " + e.getMessage());
        return false;
    }

    // Replace the original file with the temporary file
    File tempFile = new File("./temp.txt");
    File originalFile = new File("./availableRooms.txt");
    if (tempFile.exists()) {
        if (originalFile.exists()) {
            originalFile.delete(); // Delete the original file
        }
        if (!tempFile.renameTo(originalFile)) {
            System.err.println("Error replacing original file");
            return false;
        }
    } else {
        System.err.println("Temporary file not found");
        return false;
    }

    return true;
}


    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./availableRooms.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                // Assuming that "Booked" is the status for booked rooms
                if (data.length >= 3 && data[2].trim().equals("Booked")) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CheckoutPage());
    }
}
