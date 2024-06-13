package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminHomePage extends JFrame implements ActionListener {
    JButton btnBookFlight, btnViewBookedFlights , btnCancelTicket, btnViewFlights,btnAddFlightJourney,btnPassengerDetailsView,btnUpdateFlightJourney,btnUpdatePassenger,btnViewFlightJourney,btnAddEmployee, btnLogOut;
    JLabel backgroundLabel;

    public AdminHomePage() {
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
        btnAddFlightJourney = new JButton("Add Flight Journey");
        btnPassengerDetailsView = new JButton("Passenger Details View");
        btnUpdateFlightJourney = new JButton("Update Flight Journey");
        btnUpdatePassenger = new JButton("Update Passenger");
        btnViewFlightJourney = new JButton("View Flight Journey");
        btnAddEmployee = new JButton ("Add Employee");
        btnLogOut = new JButton ("LogOut");

        setLayout(new FlowLayout());

        add(btnBookFlight);
        add(btnViewBookedFlights);
        add(btnCancelTicket);
        add(btnViewFlights);
        add(btnAddFlightJourney);
        add(btnPassengerDetailsView);
        add(btnUpdateFlightJourney);
        add(btnUpdatePassenger);
        add(btnViewFlightJourney);
        add(btnAddEmployee);
        add(btnLogOut);

        btnBookFlight.addActionListener(this);
        btnViewBookedFlights.addActionListener(this);
        btnCancelTicket.addActionListener(this);
        btnViewFlights.addActionListener(this);
        btnAddFlightJourney.addActionListener(this);
        btnPassengerDetailsView.addActionListener(this);
        btnUpdateFlightJourney.addActionListener(this);
        btnUpdatePassenger.addActionListener(this);
        btnViewFlightJourney.addActionListener(this);
        btnAddEmployee.addActionListener(this);
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
        } else if (e.getSource() == btnAddFlightJourney){
            new AddFlightJourney();
        } else if (e.getSource() == btnPassengerDetailsView){
            new PassengerDetailsView();
        } else if (e.getSource() == btnUpdateFlightJourney){
            new UpdateFlightJourney();
        } else if (e.getSource() == btnUpdatePassenger){
            new UpdatePassenger();
        } else if (e.getSource() == btnViewFlightJourney){
            new ViewFlightJourney();
        } else if (e.getSource() == btnAddEmployee){
            new AddEmployee();
        } else if (e.getSource() == btnLogOut){
            new MainHomePage();
        }
    }

    public static void main(String[] args) {
        new AdminHomePage();
    }
}
