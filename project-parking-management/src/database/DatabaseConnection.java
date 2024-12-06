package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {



    public static Connection getConnection() throws SQLException{
           String URL = "jdbc:mysql://localhost:3306/parking_management_sourcemind";
           String USER = "root";
           String PASSWORD = "";

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            throw new RuntimeException(e);
        }
  }



}
