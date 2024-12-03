package database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final   String URL = "jdbc:mysql://localhost/parking_management";
    private static final String USER = "root";
    private static final  String PASSWORD = "";

    public static Connection connect() throws SQLException{
      Driver dv = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(dv);
      return DriverManager.getConnection(URL, USER, PASSWORD);
  }

   /* public static void main(String[] args) {
        try {
            System.out.println("Connexion a reussie ! " + connect());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


}
