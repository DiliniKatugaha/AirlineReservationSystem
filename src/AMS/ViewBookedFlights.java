
package AMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class ViewBookedFlights extends JFrame implements ActionListener {

    private JLabel backgroundLabel;
    private JLabel topicLabel;
    private JTable table;
    private ConnectionClass connectionClass;
    private JButton backButton;

    public ViewBookedFlights() {
        setTitle("View Booked Flights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        
        connectionClass = new ConnectionClass();

        
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(1366 , 768, Image.SCALE_SMOOTH)));
        backgroundLabel.setBounds(0, 0, 1366 , 768);
        add(backgroundLabel);

       
        topicLabel = new JLabel("Booked Flights");
        topicLabel.setBounds(250, 30, 300, 50);
        topicLabel.setForeground(Color.WHITE);
        topicLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(topicLabel);

        
        backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(30, 20, 150, 40);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        
        table = new JTable();
        loadBookedFlightsData();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 700, 400);
        backgroundLabel.add(scrollPane);

        setVisible(true);
    }

    private void loadBookedFlightsData() {
        try {
            
            String query = "SELECT * FROM bookings";
            ResultSet rs = connectionClass.stm.executeQuery(query);

            
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

            
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            
            this.dispose(); 
        }
    }

    public static void main(String[] args) {
        new ViewBookedFlights();
    }
}

