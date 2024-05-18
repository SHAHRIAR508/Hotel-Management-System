
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;



public class Login extends JFrame implements ActionListener {
    JLabel userNameLabel, userPassLabel, label;
    JTextField userName;
    JPasswordField password;
    JButton btnLogin;

    Font font = new Font("Cambria", Font.BOLD, 20);
    Font font2 = new Font("Cambria", Font.BOLD, 25);

    public Login() {
        super("Login Page");
        this.setSize(600, 400); // Adjusted size
        this.setLocationRelativeTo(null); // Center the frame on the screen
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background Image
        ImageIcon imageIcon = new ImageIcon("resources/hotel2.jpg");
        Image backgroundImage = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 600, 400);
        this.add(background);

        // Welcome Label
        label = new JLabel("Welcome to Our Hotel");
        label.setBounds(150, 50, 400, 40); // Adjusted position
        label.setFont(font2);
        label.setForeground(Color.WHITE);
        background.add(label);

        // USER NAME Label
        userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(75, 120, 150, 25); // Adjusted position
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.WHITE);
        background.add(userNameLabel);

        // USER NAME TextField
        userName = new JTextField("");
        userName.setBounds(200, 120, 150, 25); // Adjusted position
        userName.setFont(font);
        background.add(userName);

        // User Password Label
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(75, 160, 100, 25); // Adjusted position
        userPassLabel.setFont(font);
        userPassLabel.setForeground(Color.WHITE);
        background.add(userPassLabel);

        // User Password Password Field
        password = new JPasswordField("");
        password.setBounds(200, 160, 150, 25); // Adjusted position
        password.setEchoChar('*');
        password.setFont(font);
        background.add(password);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(250, 200, 90, 30); // Adjusted position
        btnLogin.setFont(font);
        btnLogin.addActionListener(this);
        background.add(btnLogin);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String name = userName.getText();
            String pass = String.valueOf(password.getPassword());

            if (FileIO.checkUser(name, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successfull");
                new HotelPage();
                userName.setText("");
                password.setText("");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid User Name or Password", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
