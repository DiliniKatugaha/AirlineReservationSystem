
package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainHomePage extends JFrame implements ActionListener {
    JButton btnAdmin, btnEmployee , btnCustomer;
    JLabel backgroundLabel;

    public MainHomePage() {
        setTitle("Airline Management System - Home");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/2.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        setContentPane(backgroundLabel);

        
        btnAdmin = new JButton("Admin Login");
        btnEmployee = new JButton("Employee Login");
        btnCustomer = new JButton ("Customer Login");
        

        setLayout(new FlowLayout());

        add(btnAdmin);
        add(btnEmployee);
        add(btnCustomer);

        btnAdmin.addActionListener(this);
        btnEmployee.addActionListener(this);
        btnCustomer.addActionListener(this);
        

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdmin) {

            new AdminLogIn();
        } else if (e.getSource() == btnEmployee) {

            new EmployeeLogIn();
        } else if (e.getSource() == btnCustomer){
            new SignIn();
        } 
    }

    public static void main(String[] args) {
        new MainHomePage();
    }
}
