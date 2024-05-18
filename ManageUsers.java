import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.table.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;



public class ManageUsers extends JFrame implements ActionListener {
    Font font = new Font("Lucida Sans Unicode", Font.PLAIN, 17);
    Font font2 = new Font("Bookman Old Style", Font.BOLD, 40);
	Font cmnt = new Font("Lucida Sans Unicode", Font.PLAIN, 13);
	
    JPanel panel;
    JLabel label;

    JTextField TName, TAge, TId, TBP, searchField;
    JButton add, remove, back, search, refresh, edit, update;

    DefaultTableModel model;
    JTable table;
	JComboBox<String> comboBox;

    public ManageUsers() {
		super("Manage Users");
        initializeFrame();
        createTable();

        // Display the window after all initialization
        this.setVisible(true);
    }

    public void initializeFrame() {
	
    this.setSize(1300, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null); // Center the frame on the screen
    this.setLayout(null);

    // Creating a Panel Container
    panel = new JPanel();
    panel.setBounds(0, 0, 1300, 1000);
    panel.setOpaque(false);
    panel.setLayout(null);
    this.add(panel); 

   // Creating Labels
label = new JLabel("Manage Users");
label.setBounds(470, 10, 400, 50); // Adjusted position and size
label.setFont(font2);
label.setForeground(Color.WHITE); // Set foreground color to white

JLabel idLabel = new JLabel("User Id");
idLabel.setFont(font);
idLabel.setBounds(40, 60, 100, 40);
idLabel.setForeground(Color.WHITE); // Set foreground color to white



JLabel commentLabel = new JLabel("(optional)");
commentLabel.setFont(cmnt);
commentLabel.setBounds(100, 60, 100, 40);
commentLabel.setForeground(new Color(156, 181, 162)); // Set foreground color to white


JLabel nameLabel = new JLabel("Name");
nameLabel.setFont(font);
nameLabel.setBounds(170, 60, 100, 40);
nameLabel.setForeground(Color.WHITE); // Set foreground color to white

JLabel ageLabel = new JLabel("Age");
ageLabel.setFont(font);
ageLabel.setBounds(40, 140, 100, 40);
ageLabel.setForeground(Color.WHITE); // Set foreground color to white

JLabel phoneNumberLabel = new JLabel("Phone Number");
phoneNumberLabel.setFont(font);
phoneNumberLabel.setBounds(380, 60, 140, 40);
phoneNumberLabel.setForeground(Color.WHITE); // Set foreground color to white

JLabel genderLabel = new JLabel("Gender");
genderLabel.setFont(font);
genderLabel.setBounds(170, 140, 100, 40);
genderLabel.setForeground(Color.WHITE); // Set foreground color to white


JLabel searchLabel = new JLabel("Id/Phone number");
searchLabel.setFont(font);
searchLabel.setBounds(890, 60, 180, 40);
searchLabel.setForeground(Color.WHITE); // Set foreground color to white


comboBox = new JComboBox(new String[]{"Male","Female"});
comboBox.setBounds(170, 180, 100, 40);
comboBox.setFont(font);
this.add(comboBox);


    // TextFields
    TId = new JTextField("");
    TId.setFont(font);
    TId.setBounds(40, 100, 100, 40);

    TName = new JTextField("");
    TName.setFont(font);
    TName.setBounds(170, 100, 180, 40);

    TAge = new JTextField("");
    TAge.setFont(font);
    TAge.setBounds(40, 180, 100, 40);

    TBP = new JTextField(""); // Renamed variable to TPhoneNumber
    TBP.setFont(font);
    TBP.setBounds(380, 100, 180, 40);

        // Creating Buttons
        add = new JButton("Add User");
        add.setFont(font);
        add.setBounds(1000, 630, 165, 40); 
        add.setForeground(Color.WHITE);
        add.setBackground(new Color(36, 214, 42));
        add.addActionListener(this);

        remove = new JButton("Delete User");
        remove.setFont(font);
        remove.setBounds(1000, 780, 165, 40);
        remove.setForeground(Color.WHITE);
        remove.setBackground(new Color(214, 36, 36));
        remove.addActionListener(this);

        back = new JButton("<Back");
        back.setBounds(40, 780, 100, 40);
        back.setFont(font);
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(214, 36, 36));
        back.addActionListener(this);
        this.add(back);

        // Search Button
        search = new JButton("Search");
        search.setFont(font);
        search.setBounds(1080, 100, 100, 40);
        search.setForeground(Color.WHITE);
        search.setBackground(new Color(76, 141, 245));
        search.addActionListener(this);

        searchField = new JTextField();
        searchField.setFont(font);
        searchField.setBounds(890, 100, 180, 40);

        // Refresh Button
        refresh = new JButton("Refresh");
        refresh.setFont(font);
        refresh.setBounds(40, 730, 100, 40);
        refresh.setForeground(Color.WHITE);
        refresh.setBackground(new Color(76, 141, 245));
        refresh.addActionListener(this);
        panel.add(refresh);
		
		
		// Edit Button
        edit = new JButton("Edit User");
        edit.setFont(font);
        edit.setBounds(1000, 680, 165, 40);
        edit.setForeground(Color.WHITE);
        edit.setBackground(new Color(255, 176, 31));
        edit.addActionListener(this);
        panel.add(edit);
		
		// Update Button
        update = new JButton("Save Changes");
        update.setFont(font);
        update.setBounds(1000, 730, 165, 40);
        update.setForeground(Color.WHITE);
        update.setBackground(new Color(255, 176, 31));
        update.addActionListener(this);
        panel.add(update);		
		
		
		//BackGround 
		ImageIcon image = new ImageIcon("./resources/manage22.jpg");
		JLabel background = new JLabel();
		background.setBounds(0, 0, 1300, 1000);
		background.setIcon(image);
		this.add(background);

        // adding components to Panel
        panel.add(label);
        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(searchField);
        panel.add(phoneNumberLabel);
        panel.add(TId);
        panel.add(TName);
        panel.add(TAge);
        panel.add(TBP);
        panel.add(add);
        panel.add(remove);
        panel.add(back);
        panel.add(search);
		panel.add(commentLabel);
		panel.add(genderLabel);
		panel.add(searchLabel);
    }

    public void createTable() {
        // Create Model For Table
        model = new DefaultTableModel();
        model.addColumn("User Id");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Phone number");
    

        // Create Table with model
        table = new JTable(model);
        table.setFont(font);
        table.setBackground(new Color(146, 211, 247));
        table.setBounds(0, 0, 900, 350);
        table.setRowHeight(40);
        table.setSelectionBackground(new Color(247, 146, 146));
        table.getTableHeader().setFont(font);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 240, 900, 350);

        readFileNa();
        panel.add(scrollPane);
    }
	
	
	public void EditData(String id){
        if(FileIO.checkUserExits(id)){
			try{
			Scanner sc = new Scanner(new File("./customers.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				if(id.equals(data[1])){
		    TId.setText(data[1]);
            TName.setText(data[0]);
            TAge.setText(data[2]);
		    TBP.setText(data[4]);
			 break;
				}
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
			
			
		}else{
		 JOptionPane.showMessageDialog(this, "User Not Found");		
		} 	
	}
	
	public void GenerateUserID(){
		
		
	}

    public void deleteData(String id) {
    try {
        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get("./customers.txt"));

        // Create a new list without the line containing the specified ID
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(";");
            if (!data[1].equals(id)) {
                updatedLines.add(line);
            }
        }

        // Write the updated lines to a new file
        Path tempFile = Files.createTempFile("temp", ".txt");
        Files.write(tempFile, updatedLines, StandardCharsets.UTF_8);

        // Delete the original file and rename the temporary file
        Files.delete(Paths.get("./customers.txt"));
        Files.move(tempFile, Paths.get("./customers.txt"), StandardCopyOption.REPLACE_EXISTING);

        refreshTable();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void UpdateData(String roomID, String name, String age, String gender, String number){
		
		deleteData(roomID);
		AddData(roomID, name, age, gender, number);
		
	}

    public void readFileNa() {
        try {
            Scanner sc = new Scanner(new File("./customers.txt"));
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(";");

                if (data.length == 5) {
                    String name = data[0];
                    String id = data[1];
                    String age = data[2];
                    String gender = data[3];
                    String number = data[4];
                
                    model.addRow(new Object[]{id, name, age, gender, number});
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public void AddData(String roomID, String name, String age, String gender, String number) {       

	   try {
            FileWriter fw = new FileWriter(new File("./customers.txt"), true);
            fw.write(name + ";" + roomID + ";" + age + ";" + gender + ";" + number + ";"+"\n");
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(this, "New user successfully created");
            refreshTable();

        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }

    private void refreshTable() {
        // Clear the existing data from the table model
        model.setRowCount(0);

        // Call the readFileNa() method to reload the data from the file
        readFileNa();
		TId.setText("");
        TName.setText("");
        TAge.setText("");
		TBP.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (add == e.getSource()) {
            String tid = TId.getText().trim();
            String tname = TName.getText();
            String tage = TAge.getText();
            String tnumber = TBP.getText();
			String tgender = (String)comboBox.getSelectedItem();
		if (tname.isEmpty() || tage.isEmpty() || tnumber.isEmpty() || tgender.isEmpty()){
			 
			 JOptionPane.showMessageDialog(this, "Please enter data correctly"); 
			
		}else{
       
            AddData(tid, tname, tage, tgender, tnumber);

            TId.setText("");
            TName.setText("");
            TAge.setText("");
		    TBP.setText(""); }

        } else if (remove == e.getSource()) {
			String id = TId.getText();
			if (id.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please enter an ID");  }
			else{
			if(FileIO.checkUserExits(id)){
			int option = JOptionPane.showConfirmDialog(this,"Are you sure you want to remove?");
            if(option == JOptionPane.YES_OPTION){			
            deleteData(id); 
			TId.setText("");}}
			else{
				JOptionPane.showMessageDialog(this, "User Not Found");
			}
        } 
		}
	
		else if (search == e.getSource()) {
            String searchText = searchField.getText().trim(); // Get the search text and remove leading/trailing spaces
            boolean found = false;

            // Iterate over each row in the table
            for (int i = 0; i < table.getRowCount(); i++) {
                String roomNumber = (String) table.getValueAt(i, 0); // Get the room number from the first column
                String phoneNumber = (String) table.getValueAt(i, 4);    
					
                // Check if the room number matches the search text (case-insensitive)
                if (roomNumber.equalsIgnoreCase(searchText)) {
                    table.setRowSelectionInterval(i, i); // Select the matching row
                    table.setSelectionBackground(new Color(50, 240, 91)); // Set the selection background color
                    found = true;
                    break;
                }else if(phoneNumber.equalsIgnoreCase(searchText)){
                    table.setRowSelectionInterval(i, i); // Select the matching row
                    table.setSelectionBackground(new Color(50, 240, 91)); // Set the selection background color
                    found = true;
                    break;					
				}
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Found at row: " + (table.getSelectedRow() + 1));
            } else {
                JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
		
	
		
		else if (refresh == e.getSource()) {
			
			  refreshTable();
			  
        } else if (back == e.getSource()) {
			new HotelPage();
            this.dispose();
        } else if(edit == e.getSource()){
			String id = TId.getText(); 
			if(id.isEmpty()){
			  JOptionPane.showMessageDialog(this, "Please enter an ID"); 
			}else{
			EditData(id); }
			
		} else if(update== e.getSource()){
			String tid = TId.getText().trim();
            String tname = TName.getText();
            String tage = TAge.getText();
            String tnumber = TBP.getText();
			String tgender = (String)comboBox.getSelectedItem();
		if (tname.isEmpty() || tage.isEmpty() || tnumber.isEmpty() || tgender.isEmpty()){
			 
			 JOptionPane.showMessageDialog(this, "Please enter data correctly"); 
			
		}else{
		UpdateData(tid, tname, tage, tnumber, tgender); }
			
		}
    }
}
