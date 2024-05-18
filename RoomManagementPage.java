import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class RoomManagementPage extends JFrame implements ActionListener {
    Font font = new Font("Lucida Sans Unicode", Font.PLAIN, 16);
    Font font2 = new Font("Bookman Old Style", Font.BOLD, 40);
    JPanel panel;

    JLabel label;
    JComboBox<String> comboBox;

    JTextField TextId, TAge, TRN, TGender, TBD, searchField;
    JButton add, remove, back, search, refresh;

    DefaultTableModel model;
    JTable table;

    String username; 
    String userphonenumber;

    public RoomManagementPage() {
        initializeFrame();
        createTable();

        // Display the window after all initialization
        this.setVisible(true);
    }

    public void initializeFrame() {
        this.setSize(1300, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setLayout(null);

        panel = new JPanel();
        panel.setBounds(0, 0, 1300, 1000);
        panel.setOpaque(false);
        panel.setLayout(null);
        this.add(panel);

        label = new JLabel("Manage Bookings");
        label.setBounds(470, 10, 400, 50); 
        label.setFont(font2);
        label.setForeground(Color.WHITE); 

        JLabel idLabel = new JLabel("ID/Phone number");
        idLabel.setFont(font);
        idLabel.setBounds(40, 60, 200, 40);
        idLabel.setForeground(Color.WHITE); 

        JLabel roomLabel = new JLabel("Room number");
        roomLabel.setFont(font);
        roomLabel.setBounds(240, 60, 200, 40);
        roomLabel.setForeground(Color.WHITE); 

        JLabel bookingDaysLabel = new JLabel("Booking days");
        bookingDaysLabel.setFont(font);
        bookingDaysLabel.setBounds(440, 60, 140, 40);
        bookingDaysLabel.setForeground(Color.WHITE); 

        JLabel paymentLabel = new JLabel("Payment");
        paymentLabel.setFont(font);
        paymentLabel.setBounds(40, 170, 100, 40);
        paymentLabel.setForeground(Color.WHITE); 

        comboBox = new JComboBox<>(new String[]{"Pending", "Paid"});
        comboBox.setBounds(120, 170, 100, 40);
        comboBox.setFont(font);
        panel.add(comboBox);

        TRN = new JTextField("");
        TRN.setFont(font);
        TRN.setBounds(40, 100, 180, 40);

        TextId = new JTextField("");
        TextId.setFont(font);
        TextId.setBounds(240, 100, 180, 40);

        TBD = new JTextField("");
        TBD.setFont(font);
        TBD.setBounds(440, 100, 180, 40);

        add = new JButton("Add Booking");
        add.setFont(font);
        add.setBounds(1000, 680, 165, 40);
        add.setForeground(Color.WHITE);
        add.setBackground(new Color(36, 214, 42));
        add.addActionListener(this);

        remove = new JButton("Cancel Booking");
        remove.setFont(font);
        remove.setBounds(1000, 730, 165, 40);
        remove.setForeground(Color.WHITE);
        remove.setBackground(new Color(214, 36, 36));
        remove.addActionListener(this);

        back = new JButton("Back>");
        back.setBounds(1000, 780, 100, 40);
        back.setFont(font);
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(214, 36, 36));
        back.addActionListener(this);
        this.add(back);

        search = new JButton("Search");
        search.setFont(font);
        search.setBounds(800, 100, 100, 40);
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(76, 141, 245));
        search.addActionListener(this);

        searchField = new JTextField();
        searchField.setFont(font);
        searchField.setBounds(930, 100, 100, 40);

        refresh = new JButton("Refresh");
        refresh.setFont(font);
        refresh.setBounds(1000, 630, 165, 40);
        refresh.setForeground(Color.WHITE);
        refresh.setBackground(new Color(76, 141, 245));
        refresh.addActionListener(this);
        panel.add(refresh);

        ImageIcon image = new ImageIcon("./resources/hotelm.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 1300, 1000);
        background.setIcon(image);
        this.add(background);

        panel.add(label);
        panel.add(idLabel);
        panel.add(roomLabel);
        panel.add(paymentLabel);

        panel.add(searchField);
        panel.add(bookingDaysLabel);
        panel.add(TRN);
        panel.add(TextId);

        panel.add(TBD);
        panel.add(add);
        panel.add(remove);
        panel.add(back);
        panel.add(search);
    }

    public void createTable() {
        model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Room Type");
        model.addColumn("Customer Name");
        model.addColumn("Phone number");
        model.addColumn("Booking Days");
        model.addColumn("Total Amount");
        model.addColumn("Payment Status");

        table = new JTable(model);
        table.setFont(font);
        table.setBackground(new Color(146, 211, 247));
        table.setBounds(0, 0, 1100, 350);
        table.setRowHeight(40);
        table.setSelectionBackground(new Color(247, 146, 146));
        table.getTableHeader().setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 240, 1100, 350);

        readFileNa();
        panel.add(scrollPane);
    }

    public static boolean checkUserExists(String id) {
        boolean found = false;
        try {
            Scanner sc = new Scanner(new File("./customers.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                if (id.equals(data[0])) {
                    found = true;
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        return found;
    }

    public static boolean checkRoomStatus(String roomnumber) {
        boolean available = false;

        try {
            Scanner sc = new Scanner(new File("./availableRooms.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                if (roomnumber.equals(data[0])) {
                    String status = data[2];
                    if (status.equals("Available")) {
                        available = true;
                    }
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        return available;
    }

    public static boolean checkRoomExists(String roomnumber) {
        boolean found = false;

        try {
            Scanner sc = new Scanner(new File("./availableRooms.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                if (roomnumber.equals(data[0])) {
                    found = true;
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        return found;
    }

    public void addBooking(String roomNumber, String roomType, String amountRoom, String name, String phoneNumber, String bookingDays, String paymentAmount, String txID, String paymentStatus) {
        try {
            FileWriter fw = new FileWriter(new File("./bookings.txt"), true);
            fw.write(roomNumber + ";" + roomType + ";" + amountRoom + ";" + name + ";" + phoneNumber + ";" + bookingDays + ";" + paymentAmount + ";" + txID + ";" + paymentStatus + ";\n");
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }

    public int getAmount(String roomNumber, String Bookdays) {
        int finalAmount = 0;
        try {
            Scanner sc = new Scanner(new File("./availableRooms.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                if (roomNumber.equals(data[0])) {
                    String price = data[3];
                    int amount = Integer.parseInt(price);
                    int days = Integer.parseInt(Bookdays);
                    finalAmount = amount * days;
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        return finalAmount;
    }

    public String getRoomType(String roomId) {
        try {
            Scanner sc = new Scanner(new File("./availableRooms.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                if (roomId.equals(data[0])) {
                    String type = data[1];
                    return type;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        return null;
    }

    public void readFileNa() {
        try {
            Scanner sc = new Scanner(new File("./bookings.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");

                if (data.length == 9) {
                    String roomNumber = data[0];
                    String roomType = data[1];
                    String cName = data[3];
                    String pNumber = data[4];
                    String bDays = data[5];
                    String tAmount = data[6];
                    String pStatus = data[8];
                    model.addRow(new Object[]{roomNumber, roomType, cName, pNumber, bDays, tAmount, pStatus});
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        readFileNa();
    }

    public void actionPerformed(ActionEvent e) {
        if (add == e.getSource()) {
            String tid = TRN.getText();
            if (checkUserExists(tid)) { 
                String trn = TextId.getText();
                String tstatus = (String) comboBox.getSelectedItem();
                String booking = TBD.getText();
                String type = getRoomType(trn);
                int taka = getAmount(trn, booking);
                String amount = Integer.toString(taka);
                String txid = "dfdfdfdfdf";

                if (checkRoomExists(trn) && checkRoomStatus(trn)) { 
                    try {
                        Scanner sc = new Scanner(new File("./customers.txt"));
                        while (sc.hasNextLine()) {
                            String[] data = sc.nextLine().split(";");
                            if (tid.equals(data[0])) {
                                username = data[1]; 
                                userphonenumber = data[4]; 
                                break;
                            }
                        }
                        sc.close();
                    } catch (Exception ex) {
                        System.out.println("File Not Found");
                    }

                    addBooking(trn, type, tid, username, userphonenumber, booking, amount, txid, tstatus);
                } else {
                    JOptionPane.showMessageDialog(this, "Room does not exist or is not available!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (remove == e.getSource()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { 
                String roomNumber = (String) table.getValueAt(selectedRow, 0); 
                String phoneNumber = (String) table.getValueAt(selectedRow, 3); 
                cancelBooking(roomNumber, phoneNumber); 
                refreshTable(); 
            } else {
                JOptionPane.showMessageDialog(this, "Please select a booking to cancel.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (search == e.getSource()) {
            String searchText = searchField.getText().trim(); 
            boolean found = false;

            for (int i = 0; i < table.getRowCount(); i++) {
                String roomNumber = (String) table.getValueAt(i, 0); 

                if (roomNumber.equalsIgnoreCase(searchText)) {
                    table.setRowSelectionInterval(i, i); 
                    table.setSelectionBackground(new Color(50, 240, 91)); 
                    found = true;
                    break;
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Found at row: " + (table.getSelectedRow() + 1));
            } else {
                JOptionPane.showMessageDialog(this, "Cannot find the room number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (refresh == e.getSource()) {
            refreshTable();
        } else if (back == e.getSource()) {
            this.dispose();
            new HotelPage();
        }
    }

    private void cancelBooking(String roomNumber, String phoneNumber) {
        try {
            File inputFile = new File("./bookings.txt");
            File tempFile = new File("./tempBookings.txt");

            Scanner sc = new Scanner(inputFile);
            FileWriter fw = new FileWriter(tempFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                if (!(roomNumber.equals(data[0]) && phoneNumber.equals(data[4]))) {
                    fw.write(line + "\n");
                }
            }

            sc.close();
            fw.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temporary file.");
            }
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RoomManagementPage();
    }
}
