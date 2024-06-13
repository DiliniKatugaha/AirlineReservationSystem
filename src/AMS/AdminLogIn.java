package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogIn extends JFrame implements ActionListener {
    JLabel L1, L2, L3, L4;
    JButton bt1, bt2;
    JPasswordField pf;
    JTextField tf;

    AdminLogIn() {
        setTitle("Admin Sign In");
        setBackground(Color.white);
        setLayout(null);

        L1 = new JLabel();
        L1.setBounds(0, 0, 1366 , 768);
        L1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/2.jpg"));
        Image i1 = img.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i1);
        L1.setIcon(img2);
        add(L1);

        L3 = new JLabel("Admin Sign In");
        L3.setBounds(190, 30, 500, 50);
        L3.setForeground(Color.BLACK);
        L3.setFont(new Font("Arial", Font.BOLD, 30));
        L1.add(L3);

        L2 = new JLabel("Username");
        L2.setBounds(120, 120, 150, 30);
        L2.setForeground(Color.BLACK);
        L2.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L2);

        tf = new JTextField();
        tf.setBounds(320, 120, 150, 30);
        L1.add(tf);

        L4 = new JLabel("Password");
        L4.setBounds(120, 170, 150, 30);
        L4.setForeground(Color.BLACK);
        L4.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L4);

        pf = new JPasswordField();
        pf.setBounds(320, 170, 150, 30);
        L1.add(pf);

        bt1 = new JButton("Sign In");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.white);
        bt1.setBounds(120, 220, 150, 40);
        bt1.addActionListener(this);
        L1.add(bt1);

        bt2 = new JButton("Back");
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.white);
        bt2.setBounds(320, 220, 150, 40);
        bt2.addActionListener(this);
        L1.add(bt2);

        setSize(580, 350);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf.getText();
            String pass = new String(pf.getPassword());

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "select * from admin where username = ? and password = ?";
                PreparedStatement pstmt = obj.con.prepareStatement(q);
                pstmt.setString(1, username);
                pstmt.setString(2, pass);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    new AdminHomePage().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "You entered wrong user name or password!");
                }

                
                rs.close();
                pstmt.close();
                obj.close();

            } catch (HeadlessException | SQLException ex) {
            }

            System.out.println("Sign In button clicked");
        } else if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AdminLogIn();
    }
}



