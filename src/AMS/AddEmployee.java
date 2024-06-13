
package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener {

    JLabel L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12;
    JTextField tf1, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    JButton bt1, bt2;
    JPasswordField pf;
    JFrame f;

    AddEmployee() {
        f = new JFrame("Sign Up");
        f.setBackground(Color.white);
        f.setLayout(null);

        L1 = new JLabel();
        L1.setBounds(0, 0, 1366 , 768);
        L1.setLayout(null);

       
        try {
            ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/login.jfif"));
            Image i1 = img.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH);
            ImageIcon img2 = new ImageIcon(i1);
            L1.setIcon(img2);
        } catch (Exception e) {
            System.out.println("Image not found");
            e.printStackTrace();
        }
        
        add(L1);

        L3 = new JLabel("Welcome to the Airline Sri Lanka");
        L3.setBounds(250, 30, 550, 50);
        L3.setForeground(Color.BLACK);
        L3.setFont(new Font("Arial", Font.BOLD, 30));
        L1.add(L3);
        f.add(L1);

        L2 = new JLabel("Username");
        L2.setBounds(50, 150, 150, 30);
        L2.setForeground(Color.BLACK);
        L2.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L2);

        tf1 = new JTextField();
        tf1.setBounds(200, 150, 150, 30);
        L1.add(tf1);

        L4 = new JLabel("Password");
        L4.setBounds(450, 150, 200, 30);
        L4.setForeground(Color.BLACK);
        L4.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L4);

        pf = new JPasswordField();
        pf.setBounds(600, 150, 150, 30);
        L1.add(pf);

        L5 = new JLabel("Age");
        L5.setBounds(50, 200, 100, 30);
        L5.setForeground(Color.BLACK);
        L5.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L5);

        tf3 = new JTextField();
        tf3.setBounds(200, 200, 150, 30);
        L1.add(tf3);

        L6 = new JLabel("Date of Birth");
        L6.setBounds(450, 200, 200, 30);
        L6.setForeground(Color.BLACK);
        L6.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L6);

        tf4 = new JTextField();
        tf4.setBounds(600, 200, 150, 30);
        L1.add(tf4);

        L7 = new JLabel("Fixed Address");
        L7.setBounds(50, 250, 150, 30);
        L7.setForeground(Color.BLACK);
        L7.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L7);

        tf5 = new JTextField();
        tf5.setBounds(200, 250, 150, 30);
        L1.add(tf5);

        L8 = new JLabel("Contact No.");
        L8.setBounds(450, 250, 200, 30);
        L8.setForeground(Color.BLACK);
        L8.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L8);

        tf6 = new JTextField();
        tf6.setBounds(600, 250, 150, 30);
        L1.add(tf6);

        L9 = new JLabel("Email Address");
        L9.setBounds(50, 300, 150, 30);
        L9.setForeground(Color.BLACK);
        L9.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L9);

        tf7 = new JTextField();
        tf7.setBounds(200, 300, 150, 30);
        L1.add(tf7);


        L11 = new JLabel("Gender");
        L11.setBounds(450, 300, 200, 30);
        L11.setForeground(Color.BLACK);
        L11.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L11);

        tf9 = new JTextField();
        tf9.setBounds(600,300, 150, 30);
        L1.add(tf9);


        bt1 = new JButton("Add Employee");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.white);
        bt1.setBounds(400, 400, 150, 40);
        bt1.addActionListener(this);
        L1.add(bt1);


        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 100);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf1.getText();
            String password = new String(pf.getPassword());
            String age = tf3.getText();
            String dob = tf4.getText();
            String address = tf5.getText();
            String contact = tf6.getText();
            String email = tf7.getText();
            String gender = tf9.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "INSERT INTO employees (username, password, age, dob, address, contact, email, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = obj.con.prepareStatement(q);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, age);
                pstmt.setString(4, dob);
                pstmt.setString(5, address);
                pstmt.setString(6, contact);
                pstmt.setString(7, email);
                pstmt.setString(8, gender);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully Signed Up!");
                f.setVisible(false);
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}



