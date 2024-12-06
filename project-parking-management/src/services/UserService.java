package services;

import database.DatabaseConnection;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Ajouter un nouvel utilisateur (create)
    public boolean registerUser(User user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()); //Hachage du mot de passe
        String query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, user.getRole());

            // Exécuter la requête
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retourne true si l'utilisateur a été enregistré

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage());
            //e.printStackTrace();
            return false;
        }


    }

    // Récuperer tous les utilsateurs
    public List<User> getAllUsers(){
        String selectAllUsersSQL = "SELECT id, username, email, password, role FROM users";
        List<User> users = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllUsersSQL);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
                user.setId(resultSet.getInt("id"));// Assigner l'ID
                users.add(user);// Ajouter l'utilisateur à la liste
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            e.printStackTrace();
        }
        return  users;
    }

    // Récupérer un utilisateur par ID (Read)
    public User getUserById(int id){
        String selectUserSQL = "SELECT id, username, email, password, role FROM users WHERE id = ?";
        User user = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)){

            // Définir l'ID dans la requête
            preparedStatement.setInt(1, id);

            // Executer la requete

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    user = new User(
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                    user.setId(resultSet.getInt("id"));
                }
            }



        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
       return user;
    }

    // Mettre à jour un utilisateur (Update)
    public boolean updateUser(int id, String username, String email, String password, String role){
        String query = "UPDATE users SET usermane = ?, email = ?,  role = ?";

        try(Connection connection = DatabaseConnection.getConnection();
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

        try(Connection connection = DatabaseConnection.getConnection();
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

        try (Connection connection = DatabaseConnection.getConnection();
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


