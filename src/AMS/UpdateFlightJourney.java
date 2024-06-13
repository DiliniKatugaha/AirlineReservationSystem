package AMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class UpdateFlightJourney extends JFrame implements ActionListener {

    private JLabel backgroundLabel;
    private JLabel topicLabel;
    private JLabel flightNumberLabel, airlineLabel, departureLabel, destinationLabel, timeLabel, durationLabel, priceLabel;
    private JTextField flightNumberField, airlineField, departureField, destinationField, timeField, durationField, priceField;
    private JButton updateButton, backButton;
    private ConnectionClass connectionClass;

    public UpdateFlightJourney() {
        setTitle("Update Flight Journey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        connectionClass = new ConnectionClass();
        if (connectionClass.con == null) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
            backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
            backgroundLabel.setBounds(0, 0, 1366 , 768);
            add(backgroundLabel);
        } catch (Exception e) {
            System.out.println("Image not found");
            e.printStackTrace();
        }

      
        topicLabel = new JLabel("Update Flight Journey");
        topicLabel.setBounds(200, 30, 400, 50);
        topicLabel.setForeground(Color.WHITE);
        topicLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(topicLabel);

    
        flightNumberLabel = new JLabel("Flight Number:");
        flightNumberLabel.setBounds(100, 100, 150, 30);
        flightNumberLabel.setForeground(Color.WHITE);
        backgroundLabel.add(flightNumberLabel);

        flightNumberField = new JTextField();
        flightNumberField.setBounds(300, 100, 150, 30);
        backgroundLabel.add(flightNumberField);

      
        airlineLabel = new JLabel("Airline:");
        airlineLabel.setBounds(100, 150, 150, 30);
        airlineLabel.setForeground(Color.WHITE);
        backgroundLabel.add(airlineLabel);

        airlineField = new JTextField();
        airlineField.setBounds(300, 150, 150, 30);
        backgroundLabel.add(airlineField);

       
        departureLabel = new JLabel("Departure:");
        departureLabel.setBounds(100, 200, 150, 30);
        departureLabel.setForeground(Color.WHITE);
        backgroundLabel.add(departureLabel);

        departureField = new JTextField();
        departureField.setBounds(300, 200, 150, 30);
        backgroundLabel.add(departureField);

        
        destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(100, 250, 150, 30);
        destinationLabel.setForeground(Color.WHITE);
        backgroundLabel.add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(300, 250, 150, 30);
        backgroundLabel.add(destinationField);

       
        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(100, 300, 150, 30);
        timeLabel.setForeground(Color.WHITE);
        backgroundLabel.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(300, 300, 150, 30);
        backgroundLabel.add(timeField);

       
        durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(100, 350, 150, 30);
        durationLabel.setForeground(Color.WHITE);
        backgroundLabel.add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(300, 350, 150, 30);
        backgroundLabel.add(durationField);

        
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(100, 400, 150, 30);
        priceLabel.setForeground(Color.WHITE);
        backgroundLabel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(300, 400, 150, 30);
        backgroundLabel.add(priceField);

        
        updateButton = new JButton("Update");
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(200, 500, 150, 40);
        updateButton.addActionListener(this);
        backgroundLabel.add(updateButton);

       
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(400, 500, 150, 40);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String flightNumber = flightNumberField.getText();
            String airline = airlineField.getText();
            String departure = departureField.getText();
            String destination = destinationField.getText();
            String time = timeField.getText();
            String duration = durationField.getText();
            String price = priceField.getText();

            try {
                String query = "UPDATE flights SET airline = ?, departure = ?, destination = ?, time = ?, duration = ?, price = ? WHERE flight_number = ?";
                PreparedStatement pst = connectionClass.con.prepareStatement(query);
                pst.setString(1, airline);
                pst.setString(2, departure);
                pst.setString(3, destination);
                pst.setString(4, time);
                pst.setString(5, duration);
                pst.setString(6, price);
                pst.setString(7, flightNumber);

                int result = pst.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Flight details updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update flight details.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == backButton) {
            this.dispose(); 
        }
    }

    public static void main(String[] args) {
        new UpdateFlightJourney();
    }
}

