package database;

import java.sql.*;


public class ParkingDatabaseSetup {

    public static void main(String[] args) {
        String databaseName = "parking_management_sourcemind";
        String useDatabaseSQL = "USE " + databaseName;

        // Commande SQL pour supprimer la base de données
        //String dropDatabaseSQL = "DROP DATABASE IF EXISTS " + databaseName;

        // Script pour la table des utilisateurs
        String createUsersTableSQL = """
               CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    password VARCHAR(100) NOT NULL,
                    role ENUM('admin', 'user') DEFAULT 'user',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
               )
               """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            // Créer la base de données
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Base de données créée avec succès : " + databaseName);

            // Utiliser la base de données
            stmt.executeUpdate(useDatabaseSQL);

            // Créer la table des utilisateurs
            stmt.executeUpdate(createUsersTableSQL);
            System.out.println("Table 'users' créée avec succès !");



        } catch (SQLException e) {
            System.out.println("Erreur lors de la configuration de la base de données : " + e.getMessage());

        }
    }
}
