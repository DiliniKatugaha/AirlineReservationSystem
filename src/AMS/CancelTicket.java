package AMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class CancelTicket extends JFrame implements ActionListener {

    private JLabel backgroundLabel, titleLabel;
    private JTextField ticketIdField;
    private JButton searchButton, backButton, cancelButton;
    private JTable table;
    private ConnectionClass connectionClass;

    public CancelTicket() {
        setTitle("Cancel Ticket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        connectionClass = new ConnectionClass();

       
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1366 , 768);
        add(backgroundLabel);

      
        titleLabel = new JLabel("Cancel Ticket");
        titleLabel.setBounds(250, 30, 300, 50);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(titleLabel);

        
        JLabel ticketIdLabel = new JLabel("Ticket Id:");
        ticketIdLabel.setBounds(150, 100, 100, 30);
        ticketIdLabel.setForeground(Color.WHITE);
        backgroundLabel.add(ticketIdLabel);

        ticketIdField = new JTextField();
        ticketIdField.setBounds(250, 100, 150, 30);
        backgroundLabel.add(ticketIdField);

        
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBounds(450, 100, 100, 30);
        searchButton.addActionListener(this);
        backgroundLabel.add(searchButton);

        
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(50, 500, 100, 30);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

       
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(350, 500, 100, 30);
        cancelButton.addActionListener(this);
        backgroundLabel.add(cancelButton);

        
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 150, 700, 300);
        backgroundLabel.add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchFlightJourney();
        } else if (e.getSource() == backButton) {
            this.dispose(); 
        } else if (e.getSource() == cancelButton) {
            cancelTicket();
        }
    }

    private void searchFlightJourney() {
        String ticketId = ticketIdField.getText();

        try {
            String query = "SELECT * FROM bookings WHERE ticket_id = ?";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, ticketId);
            ResultSet rs = pstmt.executeQuery();

            
            ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            
            table.setModel(new DefaultTableModel(data, columnNames));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching flight journey: " + ex.getMessage());
        }
    }

    private void cancelTicket() {
        String ticketId = ticketIdField.getText();

        if (ticketId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a ticket ID to cancel.");
            return;
        }

        try {
            String query = "UPDATE bookings SET status = 'Cancelled' WHERE ticket_id = ?";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, ticketId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Ticket cancelled successfully!");
                
                table.setModel(new DefaultTableModel());
                ticketIdField.setText("");
                searchFlightJourney();
            } else {
                JOptionPane.showMessageDialog(this, "Ticket cancellation failed. Ticket ID might be invalid or already cancelled.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while cancelling ticket: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}
