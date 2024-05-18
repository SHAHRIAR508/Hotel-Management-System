import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    Font titleFont = new Font("Arial", Font.PLAIN, 25);
    Font labelFont = new Font("Arial", Font.PLAIN, 20);
    JTextField name;
    JButton btn;
    public GUI() {
        super("Demo GUI");
        this.setSize(700, 600); // (width, height)
        this.setLocation(300, 100); // (x, y)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JLabel title = new JLabel("Hello this is demo!");
        title.setBounds(100, 10, 600, 50);//x,y,w,h text's location
        title.setFont(titleFont);
        this.add(title);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(10, 60, 100, 30);//x,y,w,h
        nameLabel.setFont(labelFont);
        this.add(nameLabel);

        name = new JTextField();
        name.setBounds(120, 60, 200, 30); //x,y,w,h
        name.setFont(labelFont);
        this.add(name);

        btn = new JButton("Start");
        btn.setBounds(120, 100, 100, 30);
        btn.setFont(labelFont);
        btn.addActionListener(this);
        this.add(btn);

        this.setVisible(true);
    }

   	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btn){
		if(!name.getText().isEmpty()){
		JOptionPane.showMessageDialog(this,"Welcome "+name.getText() );

		}else{
			JOptionPane.showMessageDialog(this,"Please enter a name");
		}
	}
	}
    public static void main(String[] args) {
        GUI sp = new GUI();
    }
}
