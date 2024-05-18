
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Welcome extends JFrame

{
    private JPanel panel;
    private JLabel labelWelcome;
    private JLabel labelTo;
    private JLabel label_Name;
    private JButton buttonMenu;
    private JButton buttonLogin;
    private JButton buttonSignUp;
    private JButton buttonManagerLogin;
    private JLabel imageLabel;
    
   

    public Welcome()
    {
       this.initializeComponents();

       this.setTitle("Restaurant Management System");
       this.setBounds(200,0,900,650);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true); 
    }

    public void initializeComponents()
    
    {
        this.panel = new JPanel(null);
        this.panel.setBackground(Color.decode("#CDFFFF"));
	

    //   1st line
        this.labelWelcome = new JLabel("Welcome");
        this.labelWelcome.setBounds(30,20,300,50);
        this.labelWelcome.setForeground(Color.RED);
        this.labelWelcome.setFont(new Font("Segoe UI Black", Font.BOLD, 60));
        this.panel.add(this.labelWelcome);
        
    //   2nd line
        this.labelTo = new JLabel("To");
        this.labelTo.setBounds(300,40,300,100);
        this.labelTo.setFont(new Font("Segoe UI Black", Font.PLAIN, 60));
        this.panel.add(this.labelTo);
        
    //   3rd line
        this.label_Name = new JLabel("X");
        this.label_Name.setBounds(360,50,200,100);
        this.label_Name.setForeground(Color.RED);
        this.label_Name.setFont(new Font("Segoe UI Black", Font.ITALIC, 90));
        this.panel.add(this.label_Name);
    
    //   4th line
        this.label_Name = new JLabel("Restaurent");
        this.label_Name.setBounds(430,75,200,100);
        this.label_Name.setFont(new Font("Segoe UI Black", Font.ITALIC, 30));
        this.panel.add(this.label_Name);

        //Image
        try (InputStream is = getClass().getResourceAsStream("image/1.bmp"))  {
            {
               BufferedImage backgroundImage = ImageIO.read(is);
               ImageIcon backgroundIcon = new ImageIcon(backgroundImage);
               imageLabel = new JLabel(backgroundIcon);
               imageLabel.setBounds(0, 0, 900,600);
               panel.add(imageLabel);
           }
       } catch (IOException e) {
           e.printStackTrace();
       };
      
       
        //  Menu Button
        ImageIcon Menu = new ImageIcon(getClass().getResource("Image/Menu.png"));
        this.buttonMenu = new JButton("Menu", Menu);
        this.buttonMenu.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        this.buttonMenu.setBounds(120, 480, 180, 50);
        this.buttonMenu.setBackground(Color.orange);
        this.buttonMenu.setForeground(Color.WHITE);
        this.panel.add(this.buttonMenu);
          //    adding actionListener
           this.buttonMenu.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent event)
               {
                   String buttonMenu = event.getActionCommand();
                   if(buttonMenu.equals("Menu"))
                   {
                       setVisible(false);
                       new Menu();
                   }
               }
           });


          


       
//   LogIn Button
       ImageIcon loginIcon = new ImageIcon(getClass().getResource("Image/Login_Button1.png"));
       this.buttonLogin = new JButton("Log In", loginIcon);
       this.buttonLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
       this.buttonLogin.setBounds(350, 480, 220, 50);
       this.buttonLogin.setBackground(Color.blue);
       this.buttonLogin.setForeground(Color.WHITE);
       this.panel.add(this.buttonLogin);
           //    adding actionListener
           this.buttonLogin.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent event)
               {
                   String loginButton = event.getActionCommand();
                   if(loginButton.equals("Log In"))
                   {
                       setVisible(false);
                       new Login();
                   }
               }
           });


       
//   SignUp Button
       ImageIcon SignUPIcon  = new ImageIcon(getClass().getResource("Image/signup.png")); 
       
       this.buttonSignUp = new JButton("Sign Up",SignUPIcon);
       this.buttonSignUp.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
       this.buttonSignUp.setBounds(590, 480, 180, 50);
       this.buttonSignUp.setBackground(Color.blue);
       this.buttonSignUp.setForeground(Color.WHITE);
       this.panel.add(this.buttonSignUp);
    //    adding actionListener
    this.buttonSignUp.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String signupButton = event.getActionCommand();
            if(signupButton.equals("Sign Up"))
            {
                setVisible(false);
                new SignUp();
            }
        }
    });

      
//   Manager LogIn Button
       ImageIcon ManagerLogin  = new ImageIcon(getClass().getResource("Image/manager login1.png")); 
       this.buttonManagerLogin = new JButton("Manager Log In", ManagerLogin);
       this.buttonManagerLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
       this.buttonManagerLogin.setBounds(315, 550, 250, 50);
       this.buttonManagerLogin.setBackground(Color.red);
       this.buttonManagerLogin.setForeground(Color.WHITE);
       this.panel.add(this.buttonManagerLogin);
  //    adding actionListener
           this.buttonManagerLogin.addActionListener(new ActionListener()
           {
               @Override
               public void actionPerformed(ActionEvent event)
               {
                   String ManagerloginButton = event.getActionCommand();
                   if (ManagerloginButton.equals("Manager Log In")) {

                    setVisible(false);
                    new ManagerLogin();
                }
                
               }
               
           });

  
    
        this.add(this.panel);
    
    }
}