package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddFlightJourney extends JFrame implements ActionListener {

    private JLabel backgroundLabel, titleLabel, flightNumberLabel, airlineLabel, departureLabel, destinationLabel, timeLabel, durationLabel, priceLabel;
    private JTextField flightNumberField, airlineField, departureField, destinationField, timeField, durationField, priceField;
    private JButton addButton, backButton;
    private ConnectionClass connectionClass;

    public AddFlightJourney() {
        setTitle("Add Flight Journey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null); 

        
        connectionClass = new ConnectionClass();

        
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1366 , 768);
        add(backgroundLabel);

       
        titleLabel = new JLabel("Add Flight Journey");
        titleLabel.setBounds(250, 30, 300, 50);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(titleLabel);

       
        flightNumberLabel = new JLabel("Flight Number:");
        flightNumberLabel.setBounds(50, 100, 150, 30);
        flightNumberLabel.setForeground(Color.WHITE);
        backgroundLabel.add(flightNumberLabel);

        flightNumberField = new JTextField();
        flightNumberField.setBounds(250, 100, 200, 30);
        backgroundLabel.add(flightNumberField);

        
        airlineLabel = new JLabel("Airline:");
        airlineLabel.setBounds(50, 150, 150, 30);
        airlineLabel.setForeground(Color.WHITE);
        backgroundLabel.add(airlineLabel);

        airlineField = new JTextField();
        airlineField.setBounds(250, 150, 200, 30);
        backgroundLabel.add(airlineField);

        
        departureLabel = new JLabel("Departure:");
        departureLabel.setBounds(50, 200, 150, 30);
        departureLabel.setForeground(Color.WHITE);
        backgroundLabel.add(departureLabel);

        departureField = new JTextField();
        departureField.setBounds(250, 200, 200, 30);
        backgroundLabel.add(departureField);

        
        destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(50, 250, 150, 30);
        destinationLabel.setForeground(Color.WHITE);
        backgroundLabel.add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(250, 250, 200, 30);
        backgroundLabel.add(destinationField);

        
        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 300, 150, 30);
        timeLabel.setForeground(Color.WHITE);
        backgroundLabel.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(250, 300, 200, 30);
        backgroundLabel.add(timeField);

        
        durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(50, 350, 150, 30);
        durationLabel.setForeground(Color.WHITE);
        backgroundLabel.add(durationLabel);

        durationField = new JTextField();
                durationField.setBounds(250, 350, 200, 30);
        backgroundLabel.add(durationField);

        
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 400, 150, 30);
        priceLabel.setForeground(Color.WHITE);
        backgroundLabel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(250, 400, 200, 30);
        backgroundLabel.add(priceField);

        
        addButton = new JButton("Add");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(300, 450, 100, 40);
        addButton.addActionListener(this);
        backgroundLabel.add(addButton);

        
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(100, 500, 100, 40);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addFlightJourney();
        } else if (e.getSource() == backButton) {
            this.dispose(); 
            
        }
    }

    private void addFlightJourney() {
        String flightNumber = flightNumberField.getText();
        String airline = airlineField.getText();
        String departure = departureField.getText();
        String destination = destinationField.getText();
        String time = timeField.getText();
        String duration = durationField.getText();
        String price = priceField.getText();

        try {
            String query = "INSERT INTO flights (flight_number, airline, departure, destination, time, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, flightNumber);
            pstmt.setString(2, airline);
            pstmt.setString(3, departure);
            pstmt.setString(4, destination);
            pstmt.setString(5, time);
            pstmt.setString(6, duration);
            pstmt.setString(7, price);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Flight journey added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add flight journey. Please check your input.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding flight journey: " + ex.getMessage());
        }
    }

    private void clearFields() {
        flightNumberField.setText("");
        airlineField.setText("");
        departureField.setText("");
        destinationField.setText("");
        timeField.setText("");
        durationField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        new AddFlightJourney();
    }
}
