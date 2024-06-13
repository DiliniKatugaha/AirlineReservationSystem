
package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {
    JButton btnBookFlight, btnViewBookedFlights , btnCancelTicket, btnViewFlights, btnLogOut;
    JLabel backgroundLabel;

    public HomePage() {
        setTitle("Airline Management System - Home");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/2.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        setContentPane(backgroundLabel);

        
        btnBookFlight = new JButton("Book a Flight");
        btnViewBookedFlights = new JButton("View Booked Flights");
        btnCancelTicket = new JButton ("Cancel Ticket");
        btnViewFlights = new JButton ("View Flights");
        btnLogOut = new JButton ("LogOut");

        setLayout(new FlowLayout());

        add(btnBookFlight);
        add(btnViewBookedFlights);
        add(btnCancelTicket);
        add(btnViewFlights);
        add(btnLogOut);

        btnBookFlight.addActionListener(this);
        btnViewBookedFlights.addActionListener(this);
        btnCancelTicket.addActionListener(this);
        btnViewFlights.addActionListener(this);
        btnLogOut.addActionListener(this);
        

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBookFlight) {

            new BookFlight();
        } else if (e.getSource() == btnViewBookedFlights) {

            new ViewBookedFlights();
        } else if (e.getSource() == btnCancelTicket){
            new CancelTicket();
        } else if (e.getSource() == btnViewFlights){
            new ViewFlightJourney();
        } else if (e.getSource() == btnLogOut){
            new SignIn();
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
