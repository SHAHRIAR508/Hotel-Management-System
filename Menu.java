import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu extends JFrame {

    private JPanel panel;
    private JScrollPane scroll;
    private JTable table;
    private DefaultTableModel model;
    private JLabel labelMenu;
    private JButton buttonOrder;
    private JButton buttonBack;

    public Menu() {
        initializeComponents();

        setTitle("Menu");
        setBounds(200, 50, 1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
    panel = new JPanel(null);
    panel.setBackground(Color.decode("#CDFFFF"));
    panel.setOpaque(false);

    // Set background image
    setContentPane(new JLabel(new ImageIcon("Image/menu.jpg")));
    getContentPane().setLayout(new BorderLayout());

    // Menu label
    labelMenu = new JLabel("MENU");
    labelMenu.setBounds(380, 10, 100, 30);
    labelMenu.setFont(new Font("Cascadia Mono Light", Font.BOLD, 30));
    labelMenu.setForeground(new Color(0, 0, 0));
    panel.add(labelMenu);

    // Table
    table = new JTable();
    model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{"Item Name", "Price"});
    table.setModel(model);
    table.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
    table.setSelectionBackground(Color.CYAN);
    table.setBackground(Color.WHITE);
    table.setRowHeight(40);
    table.getColumnModel().getColumn(0).setPreferredWidth(150);
    table.getColumnModel().getColumn(1).setPreferredWidth(150);
    table.setEnabled(false);

    scroll = new JScrollPane(table);
    scroll.setBounds(90, 60, 700, 250);
    scroll.setBackground(Color.decode("#CDFFFF"));
    panel.add(scroll);

    String menuFile = "Data" + File.separator + "Menu.txt";

    loadMenuData(menuFile);

    // button Order
    buttonOrder = new JButton("Order Now");
    buttonOrder.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
    buttonOrder.setBounds(450, 540, 200, 40);
    buttonOrder.setBackground(Color.blue);
    buttonOrder.setForeground(Color.WHITE);
    panel.add(buttonOrder);

    // button - BACK
    buttonBack = new JButton("Back");
    buttonBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
    buttonBack.setBounds(220, 540, 200, 40);
    buttonBack.setBackground(Color.RED);
    buttonBack.setForeground(Color.WHITE);
    panel.add(buttonBack);

    buttonBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String buttonBack = event.getActionCommand();
            if (buttonBack.equals("Back")) {
                setVisible(false);
                new Welcome();
            }
        }
    });

    buttonOrder.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String orderButton = event.getActionCommand();
            if (orderButton.equals("Order Now")) {
                setVisible(false);
                new HaveAccount();
            }
        }
    });

    add(panel);
}


    
    private void loadMenuData(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int totalLines = 0;
            while (reader.readLine() != null)
                totalLines++;

            for (int i = 0; i < totalLines; i++) {
                String line = Files.readAllLines(Paths.get(file)).get(i);
                String x = line.substring(0, 4);
                if (x.equals("Item")) {
                    final int ITEM_NAME_PREFIX_LENGTH = 12;
                    final int ITEM_PRICE_PREFIX_LENGTH = 8;

                    String itemName = Files.readAllLines(Paths.get(file)).get(i).substring(ITEM_NAME_PREFIX_LENGTH);
                    String itemPrice = Files.readAllLines(Paths.get(file)).get((i + 1)).substring(ITEM_PRICE_PREFIX_LENGTH);
                    model.addRow(new String[]{itemName, itemPrice});
                }
            }
        } catch (IOException | IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

}
