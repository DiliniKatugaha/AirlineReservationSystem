
package AMS;



import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionClass {

  Connection con;
  Statement stm;

  public ConnectionClass() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb", "root", "");
      stm = con.createStatement();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
      new ConnectionClass();
}

    void close() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}



