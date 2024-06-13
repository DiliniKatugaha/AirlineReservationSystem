
package AMS;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;

public class BookFlight extends JFrame implements ActionListener {
    JLabel L1, L2, L3, L5, L6, L8, L9, L10, L12, L13, L14, L15;
    JTextField tf1, tf5, tf8;
    JButton bt1, bt2;
    JComboBox<String> cb2, cb3, cb4;
    JDateChooser dateChooser1, dateChooser2;
    JFrame f;
    JSpinner spinner;
    ConnectionClass connectionClass;
    JTextArea ta1;

    BookFlight() {
        f = new JFrame("Book Flight");
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

        f.add(L1);
        
        ta1 = new JTextArea();
        ta1.setBounds(650, 350, 150, 30);
        L1.add(ta1);

        L2 = new JLabel("Book Flight");
        L2.setBounds(250, 30, 550, 50);
        L2.setForeground(Color.BLACK);
        L2.setFont(new Font("Arial", Font.BOLD, 30));
        L1.add(L2);

        
        L3 = new JLabel("Ticket ID");
        L3.setBounds(50, 150, 150, 30);
        L3.setForeground(Color.BLACK);
        L3.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L3);

        tf1 = new JTextField();
        tf1.setBounds(250, 150, 150, 30);
        tf1.setEditable(false);
        tf1.setText(generateTicketId());
        L1.add(tf1);

        
        L5 = new JLabel("Booking Date");
        L5.setBounds(50, 200, 150, 30);
        L5.setForeground(Color.BLACK);
        L5.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L5);

        dateChooser1 = new JDateChooser();
        dateChooser1.setBounds(250, 200, 150, 30);
        L1.add(dateChooser1);

        
        L6 = new JLabel("Time");
        L6.setBounds(450, 150, 150, 30);
        L6.setForeground(Color.BLACK);
        L6.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L6);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        spinner = new JSpinner(model);
        spinner.setBounds(650, 150, 150, 30);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        spinner.setEditor(timeEditor);
        L1.add(spinner);

        
        L8 = new JLabel("Destination");
        L8.setBounds(450, 200, 150, 30);
        L8.setForeground(Color.BLACK);
        L8.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L8);

        String[] destinations = {"Dubai", "Melbourne", "Tokyo", "London", "Paris", "Singapore"};
        cb2 = new JComboBox<>(destinations);
        cb2.setBounds(650, 200, 150, 30);
        L1.add(cb2);

       
        L9 = new JLabel("Class");
        L9.setBounds(50, 250, 150, 30);
        L9.setForeground(Color.BLACK);
        L9.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L9);

        String[] classes = {"Economy", "Business", "First Class"};
        cb3 = new JComboBox<>(classes);
        cb3.setBounds(250, 250, 150, 30);
        cb3.addActionListener(this); 
        L1.add(cb3);

        
        L10 = new JLabel("Price");
        L10.setBounds(450, 250, 150, 30);
        L10.setForeground(Color.BLACK);
        L10.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L10);

        tf5 = new JTextField();
        tf5.setBounds(650, 250, 150, 30);
        tf5.setEditable(false);
        L1.add(tf5);

        
        L12 = new JLabel("Flight Name");
        L12.setBounds(450, 300, 150, 30);
        L12.setForeground(Color.BLACK);
        L12.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L12);

        String[] flightNames = {"SriLankan Airlines", "IndiGo", "Emirates"};
        cb4 = new JComboBox<>(flightNames);
        cb4.setBounds(650, 300, 150, 30);
        cb4.addActionListener(this); 
        L1.add(cb4);

        
        L13 = new JLabel("Journey Date");
        L13.setBounds(50, 300, 150, 30);
        L13.setForeground(Color.BLACK);
        L13.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L13);

        dateChooser2 = new JDateChooser();
        dateChooser2.setBounds(250, 300, 150, 30);
        L1.add(dateChooser2);

        
        L14 = new JLabel("Username");
        L14.setBounds(450, 350, 150, 30);
        L14.setForeground(Color.BLACK);
        L14.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L14);

        JTextArea ta1 = new JTextArea();
        ta1.setBounds(650, 350, 150, 30);
        L1.add(ta1);

        
        L15 = new JLabel("Name");
        L15.setBounds(50, 350, 150, 30);
        L15.setForeground(Color.BLACK);
        L15.setFont(new Font("Arial", Font.BOLD, 20));
        L1.add(L15);

        tf8 = new JTextField();
        tf8.setBounds(250, 350, 150, 30);
        L1.add(tf8);

        bt1 = new JButton("Book Flight");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.white);
        bt1.setBounds(200, 450, 150, 40);
        bt1.addActionListener(this);
        L1.add(bt1);

        bt2 = new JButton("Cancel");
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.white);
        bt2.setBounds(400, 450, 150, 40);
        bt2.addActionListener(this);
        L1.add(bt2);

        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(300, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cb3 || e.getSource() == cb2) {
            String flightClass = (String) cb3.getSelectedItem();
            String destination = (String) cb2.getSelectedItem();
            String price = calculatePrice(flightClass, destination);
            tf5.setText(price);
        } 
        else if (e.getSource() == bt1) {
            String ticketId = tf1.getText(); 
            String booking_date = formatDate(((JTextField) dateChooser1.getDateEditor().getUiComponent()).getText());
            String time = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().getText();
            String destination = (String) cb2.getSelectedItem();
            String flightClass = (String) cb3.getSelectedItem();
            String price = tf5.getText();
            String flightName = (String) cb4.getSelectedItem();
            String journeyDate = formatDate(((JTextField) dateChooser2.getDateEditor().getUiComponent()).getText());
            String username = ta1.getText();
            String name = tf8.getText();
            try {
                connectionClass = new ConnectionClass(); 

                String query = "INSERT INTO bookings (ticket_id,booking_date, time, destination, class, price, flight_name, journey_date, username, name, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = connectionClass.con.prepareStatement(query);

                pst.setString(1, ticketId);
                pst.setString(2, booking_date);
                pst.setString(3, time); 
                pst.setString(4, destination);
                pst.setString(5, flightClass);
                pst.setString(6, price);
                pst.setString(7, flightName);
                pst.setString(8, journeyDate); 
                pst.setString(9, username);
                pst.setString(10, name);
                pst.setString(11,"Success");

                int result = pst.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Flight Booked Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Failed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == bt2) {
            f.dispose();
        }
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateStr);
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    private String calculatePrice(String flightClass, String destination) {
      
        int basePrice = 100000;
        switch (destination) {
            case "Dubai":
                basePrice += 20000;
                break;
            case "Melbourne":
                basePrice += 40000;
                break;
            case "Tokyo":
                basePrice += 30000;
                break;
            case "London":
                basePrice += 35000;
                break;
            case "Paris":
                basePrice += 25000;
                break;
            case "Singapore":
                basePrice += 15000;
                break;
        }
        switch (flightClass) {
            case "Economy":
                basePrice *= 1;
                break;
            case "Business":
                basePrice *= 1.5;
                break;
            case "First Class":
                basePrice *= 2;
                break;
        }
        return String.valueOf(basePrice);
    }

   
    private String generateTicketId() {
       
        return "TICK" + System.currentTimeMillis();
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}


