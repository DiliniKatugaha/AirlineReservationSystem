
package AMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class PassengerDetailsView extends JFrame implements ActionListener {

    private JButton backButton;
    private JTable table;
    private ConnectionClass connectionClass;

    public PassengerDetailsView() {
        setTitle("Passenger Details");
        setLayout(new BorderLayout());

        connectionClass = new ConnectionClass();

        if (connectionClass.con == null) {
            JOptionPane.showMessageDialog(this, "Failed to connect to database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(30, 20, 150, 40);
        backButton.addActionListener(this);
        backgroundPanel.add(backButton);

        JLabel titleLabel = new JLabel("Passenger Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(250, 30, 300, 50);
        backgroundPanel.add(titleLabel);

        table = new JTable();
        loadPassengerData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 700, 400);
        backgroundPanel.add(scrollPane);

        add(backgroundPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadPassengerData() {
        if (connectionClass.stm == null) {
            JOptionPane.showMessageDialog(this, "Statement is not initialized.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "SELECT username, age, dob, address, contact, email, nationality, gender, passportid FROM passengers";
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
        new PassengerDetailsView();
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

