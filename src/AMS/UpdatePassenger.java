
package AMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class UpdatePassenger extends JFrame implements ActionListener {

    private final JComboBox<String> passengerIdComboBox;
    private JTextField usernameField, ageField, dobField, addressField, contactField, emailField, nationalityField, genderField, passportIdField;
    private JButton updateButton, backButton;
    private ConnectionClass connectionClass;

    public UpdatePassenger() {
        setTitle("Update Passenger Details");
        setLayout(new BorderLayout());

        connectionClass = new ConnectionClass();

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); 

        JLabel titleLabel = new JLabel("Update Passenger Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(200, 30, 400, 50);
        backgroundPanel.add(titleLabel);

        JLabel passengerIdLabel = new JLabel("Select Passenger ID:");
        passengerIdLabel.setBounds(50, 70, 150, 30);
        backgroundPanel.add(passengerIdLabel);

        passengerIdComboBox = new JComboBox<>();
        passengerIdComboBox.setBounds(210, 70, 200, 30);
        passengerIdComboBox.addActionListener(this);
        backgroundPanel.add(passengerIdComboBox);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 110, 100, 30);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 110, 200, 30);
        backgroundPanel.add(usernameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 150, 100, 30);
        backgroundPanel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(160, 150, 200, 30);
        backgroundPanel.add(ageField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 190, 100, 30);
        backgroundPanel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(160, 190, 200, 30);
        backgroundPanel.add(dobField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 230, 100, 30);
        backgroundPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(160, 230, 200, 30);
        backgroundPanel.add(addressField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(50, 270, 100, 30);
        backgroundPanel.add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(160, 270, 200, 30);
        backgroundPanel.add(contactField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 310, 100, 30);
        backgroundPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(160, 310, 200, 30);
        backgroundPanel.add(emailField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 350, 100, 30);
        backgroundPanel.add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(160, 350, 200, 30);
        backgroundPanel.add(nationalityField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 390, 100, 30);
        backgroundPanel.add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(160, 390, 200, 30);
        backgroundPanel.add(genderField);

        JLabel passportIdLabel = new JLabel("Passport ID:");
        passportIdLabel.setBounds(50, 430, 100, 30);
        backgroundPanel.add(passportIdLabel);

        passportIdField = new JTextField();
        passportIdField.setBounds(160, 430, 200, 30);
        backgroundPanel.add(passportIdField);

        updateButton = new JButton("Update");
        updateButton.setBounds(160, 470, 100, 30);
        updateButton.addActionListener(this);
        backgroundPanel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(270, 470, 100, 30);
        backButton.addActionListener(this);
        backgroundPanel.add(backButton);

        add(backgroundPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loadPassengerIds();
    }

    private void loadPassengerIds() {
        try {
            String query = "SELECT userid FROM passengers";
            ResultSet rs = connectionClass.stm.executeQuery(query);
            while (rs.next()) {
                passengerIdComboBox.addItem(rs.getString("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while loading passenger IDs.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updatePassengerDetails();
        } else if (e.getSource() == backButton) {
            this.dispose(); // Close current window
        } else if (e.getSource() == passengerIdComboBox) {
            loadPassengerDetails((String) passengerIdComboBox.getSelectedItem());
        }
    }

    private void loadPassengerDetails(String passportId) {
        try {
            String query = "SELECT * FROM passengers WHERE userid = ?";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, passportId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usernameField.setText(rs.getString("username"));
                ageField.setText(rs.getString("age"));
                dobField.setText(rs.getString("dob"));
                addressField.setText(rs.getString("address"));
                contactField.setText(rs.getString("contact"));
                emailField.setText(rs.getString("email"));
                nationalityField.setText(rs.getString("nationality"));
                genderField.setText(rs.getString("gender"));
                passportIdField.setText(rs.getString("passportid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while loading passenger details.");
        }
    }

    private void updatePassengerDetails() {
        String username = usernameField.getText();
        String age = ageField.getText();
        String dob = dobField.getText();
        String address = addressField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();
        String nationality = nationalityField.getText();
        String gender = genderField.getText();
        String passportId = passportIdField.getText();

        try {
            String query = "UPDATE passengers SET username=?, age=?, dob=?, address=?, contact=?, email=?, nationality=?, gender=? WHERE passportid=?";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, age);
            pstmt.setString(3, dob);
            pstmt.setString(4, address);
            pstmt.setString(5, contact);
            pstmt.setString(6, email);
            pstmt.setString(7, nationality);
            pstmt.setString(8, gender);
            pstmt.setString(9, passportId);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Passenger details updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Please check the details and try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating passenger details.");
        }
    }

    public static void main(String[] args) {
        new UpdatePassenger();
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/login.jfif"));
            backgroundImage = img.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Image not found");
            e.printStackTrace();
        }
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
