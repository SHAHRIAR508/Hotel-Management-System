

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RoomDetailsPage extends JFrame implements ActionListener {
    Font font = new Font("Lucida Sans Unicode", Font.PLAIN, 16);
    Font font2 = new Font("Bookman Old Style", Font.BOLD, 40);
    JPanel panel;

    JLabel label;
    JTextField roomNumberField, roomPriceField;
    JComboBox<String> roomTypeComboBox;
    JButton save, back;
    DefaultTableModel model;
    JTable table;

    public RoomDetailsPage() {
        initializeFrame();
        createTable();
        readFromFile(); // Read existing room details from file
        // Display the window after all initialization 
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

        // Creating Labels
        label = new JLabel("Room Details");
        label.setBounds(300, 10, 200, 50);
        label.setFont(font2);
        panel.add(label);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setFont(font);
        roomNumberLabel.setBounds(50, 100, 150, 30);
        panel.add(roomNumberLabel);

        JLabel roomPriceLabel = new JLabel("Room Price:");
        roomPriceLabel.setFont(font);
        roomPriceLabel.setBounds(50, 150, 150, 30);
        panel.add(roomPriceLabel);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setFont(font);
        roomTypeLabel.setBounds(50, 200, 150, 30);
        panel.add(roomTypeLabel);

        // TextFields
        roomNumberField = new JTextField();
        roomNumberField.setFont(font);
        roomNumberField.setBounds(200, 100, 200, 30);
        panel.add(roomNumberField);

        roomPriceField = new JTextField();
        roomPriceField.setFont(font);
        roomPriceField.setBounds(200, 150, 200, 30);
        panel.add(roomPriceField);

        // Combo Box for Room Type
        roomTypeComboBox = new JComboBox<>(new String[]{"Ordinary Room", "Duplex Room", "Luxury Room"});
        roomTypeComboBox.setFont(font);
        roomTypeComboBox.setBounds(200, 200, 200, 30);
        panel.add(roomTypeComboBox);

        // Creating Buttons
        save = new JButton("Save");
        save.setFont(font);
        save.setBounds(150, 300, 100, 40);
        save.setBackground(new Color(36, 214, 42)); // Green color
        save.setForeground(Color.WHITE); // Text color
        save.addActionListener(this);
        panel.add(save);

        back = new JButton("Back");
        back.setBounds(300, 300, 100, 40);
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
        scrollPane.setBounds(50, 360, 700, 200);
        panel.add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            // Handle saving room details
            String roomNumber = roomNumberField.getText();
            String roomType = (String) roomTypeComboBox.getSelectedItem();
            String roomPrice = roomPriceField.getText();
            String status = "Available"; // Set default status

            // Add the data to the table
            model.addRow(new Object[]{roomNumber, roomType, status, roomPrice});

            // Write to file
            writeToFile(roomNumber, roomType, status, roomPrice);

            // Clear input fields after adding
            roomNumberField.setText("");
            roomPriceField.setText("");
            roomTypeComboBox.setSelectedIndex(0);
        } else if (e.getSource() == back) {
            // Handle going back to previous page
            this.dispose();
            new HotelPage();
        }
    }

    public void writeToFile(String roomNumber, String roomType, String status, String roomPrice) {
        try (FileWriter fw = new FileWriter(new File("./availableRooms.txt"), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(roomNumber + ";" + roomType + ";" + status + ";" + roomPrice);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./availableRooms.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 4) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomDetailsPage());
    }
}
