package services;

import database.DatabaseConnection;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Ajouter un nouvel utilisateur (create)
    public boolean addUser(String username, String email, String password, String role){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); //Hachage du mot de passe
        String query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, role);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Récuperer tous les utilsateurs
    public List<User> getAllUser(){
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try(Connection connection = DatabaseConnection.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  users;
    }

    // Récupérer un utilisateur par ID (Read)
    public User getUserById(int id){
        String query = "SELECT * FROM users WHERE id = ?";

        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null; // Retourne null si l'utilisateur n'est pas trouve
    }

    // Mettre à jour un utilisateur (Update)
    public boolean updateUser(int id, String username, String email, String password, String role){
        String query = "UPDATE users SET usermane = ?, email = ?,  role = ?";

        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.setInt(4, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retourne true si la mise à jour a réussie

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un utilisateur (Delete)
    public boolean deleteUser(int id){
        String query = "DELETE FROM users WHERE id = ?";

        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retourne true si la suppression a ressuie

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Authentifier un utilisateur avec un email ou usename (vérification du mot de passe)
    public  boolean authenticate(String identify, String password){
        String query = "SELECT password FROM users WHERE username = ? OR email = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, identify);
            stmt.setString(2, identify);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword); //Verification du mot de passe
            }

        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }
        return false; //Retourne false si l'authentification échoué
    }

}


