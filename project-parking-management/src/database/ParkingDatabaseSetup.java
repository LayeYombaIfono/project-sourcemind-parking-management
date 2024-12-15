package database;

import java.sql.*;


public class ParkingDatabaseSetup {

    public static void main(String[] args) {
        String databaseName = "parking_management";
        String useDatabaseSQL = "USE " + databaseName;

        // Commande SQL pour supprimer la base de données
        //String dropDatabaseSQL = "DROP DATABASE IF EXISTS " + databaseName;

        // Script pour la table des utilisateurs
        String createUsersTableSQL = """

               CREATE TABLE IF NOT EXISTS User (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    role ENUM('admin', 'manager', 'employee') DEFAULT 'employee',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
               )
               """;
        // TABLE VEHICULE
        String createVehicleTableSQL = """
                CREATE TABLE IF NOT EXISTS Vehicle(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    licence_plate VARCHAR(20) NOT NULL UNIQUE,
                    model VARCHAR(100),
                    fuel_type ENUM('Essence', 'Diesel', 'Electric', 'Hybrid') NOT NULL,
                    owner_id INT,
                    FOREIGN KEY (owner_id) REFERENCES User(id) ON DELETE CASCADE
                
                )
                """;

        // TABLE PLACE DE PARKING
        String createParkingSpotTableSQL = """
                CREATE TABLE IF NOT EXISTS ParkingSpot(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    spot_number VARCHAR(10) NOT NULL UNIQUE,
                    is_reserved BOOLEAN DEFAULT FALSE
                
                )
                """;

        // TABLE RESERVATION DE PLACE
        String createReservationTableSQL = """
                CREATE TABLE IF NOT EXISTS Reservation(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       vehicle_id INT NOT NULL,
                       spot_id INT NOT NULL,
                       start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       end_time DATETIME DEFAULT NULL,
                       FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
                       FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id) ON DELETE CASCADE,
                       FOREIGN KEY (spot_id) REFERENCES ParkingSpot(id) ON DELETE CASCADE
                )
                """;
        // TABLE RESERVATION DE PLACE
        String createActivityLogTableSQL = """
                CREATE TABLE IF NOT EXISTS ActivityLog(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       action VARCHAR(255) NOT NULL,
                       timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
                )
                """;





        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Créer la base de données
          // String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
          //  statement.executeUpdate(createDatabaseSQL);
           // System.out.println("Base de données créée avec succès : " + databaseName);

            // Utiliser la base de données
            statement.executeUpdate(useDatabaseSQL);

            // Créer la table des utilisateurs
            statement.executeUpdate(createUsersTableSQL);
           System.out.println("Table 'users' créée avec succès !");

            // Créer la table vehicle
           statement.executeUpdate(createVehicleTableSQL);
           System.out.println("Table 'Vehicule' créée avec succès !");

            // Créer la table Place de parking
           statement.executeUpdate(createParkingSpotTableSQL);
           System.out.println("Table 'place de parking' créée avec succès !");

            // Créer la table Place de reservation
            statement.executeUpdate(createReservationTableSQL);
           System.out.println("Table 'reservation' créée avec succès !");

            // Créer la table Activité log
            statement.executeUpdate(createActivityLogTableSQL);
            System.out.println("Table activités créée avec succès !");

        // Supprimer la base de donnee
           // statement.executeUpdate(dropDatabaseSQL);
          //  System.out.println("Base de donnees suppimer avec succes...........");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la configuration de la base de données : " + e.getMessage());

        }
    }
}
