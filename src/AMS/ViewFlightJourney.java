package AMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class ViewFlightJourney extends JFrame implements ActionListener {

    private JLabel backgroundLabel, titleLabel;
    private JTextField departureField, destinationField;
    private JButton searchButton, backButton;
    private JTable table;
    private ConnectionClass connectionClass;

    public ViewFlightJourney() {
        setTitle("View Flight Journey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null); 
        
        connectionClass = new ConnectionClass();

        
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1366 , 768);
        add(backgroundLabel);

        
        titleLabel = new JLabel("View Flight Journey");
        titleLabel.setBounds(250, 30, 300, 50);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(titleLabel);

        
        JLabel departureLabel = new JLabel("Departure:");
        departureLabel.setBounds(50, 100, 100, 30);
        departureLabel.setForeground(Color.WHITE);
        backgroundLabel.add(departureLabel);

        departureField = new JTextField();
        departureField.setBounds(150, 100, 150, 30);
        backgroundLabel.add(departureField);

       
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(350, 100, 100, 30);
        destinationLabel.setForeground(Color.WHITE);
        backgroundLabel.add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(450, 100, 150, 30);
        backgroundLabel.add(destinationField);

       
        searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBounds(620, 100, 100, 30);
        searchButton.addActionListener(this);
        backgroundLabel.add(searchButton);

        
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(50, 500, 100, 30);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        
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
        }
    }

    private void searchFlightJourney() {
        String departure = departureField.getText();
        String destination = destinationField.getText();

        try {
            String query = "SELECT * FROM flights WHERE departure = ? AND destination = ?";
            PreparedStatement pstmt = connectionClass.con.prepareStatement(query);
            pstmt.setString(1, departure);
            pstmt.setString(2, destination);
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

    public static void main(String[] args) {
        new ViewFlightJourney();
    }
}
