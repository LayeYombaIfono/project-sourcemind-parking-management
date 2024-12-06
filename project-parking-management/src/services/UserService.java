package services;


import database.DatabaseConnection;
import models.User;
import org.slf4j.*;
import utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    // Ajouter un utilisateur
    @Override
    public boolean registerUser(User user) {
        String addUserSQL = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        logger.info("Tentative d'ajout d'un utilisateur : {}", user.getUsername());

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(addUserSQL)){

            //Hasher le mot de passe
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, user.getRole());

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();
            if ( rowsAffected > 0){
                logger.info("Utilisateur ajouté avec succès : {}", user.getUsername());
                return true;
            }
             // Retourne true si l'utilisateur a été enregistré

        }catch (SQLException e){
            logger.error("Erreur lors de l'ajout de l'utilisateur : {}", user.getUsername());
        }
        return false;
    }


    // Recuperer tous les utilisateurs
    @Override
    public List<User> getAllUsers(boolean includePasswords) {
        String getAllUsersSQL = includePasswords
                ? "SELECT id, username, email, password, role FROM users"
                : "SELECT id, username, email, role FROM users";

        logger.info("Récupération de tous les utilisateurs");

        List<User> users = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsersSQL)){

            //Boucle pour parcourir la liste des utilisateurs
            while (resultSet.next()){
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        includePasswords ? resultSet.getString("password"): null,
                        resultSet.getString("role")
                );
                //user.setId(resultSet.getInt("id"));// Assigner l'ID
                users.add(user);// Ajouter l'utilisateur à la liste
            }
            logger.info("Tous les utilisateurs ont été récupérés avec succès.");
        }catch (SQLException e){
            logger.error("Erreur lors de la récupération des utilisateurs : ", e);
        }

        return users;
    }

    // Récupérer un utilisateur par son id
    @Override
    public User getUserById(int id) {
        String getUserSQL = "SELECT id, username, email, role FROM users WHERE id = ?";
        logger.info("Récupération d'un utilisateur");

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)){

            // Définir l'ID dans la requête
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    User user = new User(
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            null,
                            resultSet.getString("role")
                    );

                    user.setId(resultSet.getInt("id"));
                    logger.info("L'utilisateur à été récupéré avec succès.");
                    return  user;
                }

            }
        }catch (SQLException e){
            logger.error("Erreur lors de la récupération de l'utilisateur : ", e);
        }
        return null;
    }

    // Modifier les information de l'utilisateur
    @Override
    public boolean updateUser(User user) {
        String updateUserSQL = "UPDATE users SET username = ?, email = ?, role = ? WHERE id = ?";
        logger.info("Modification de l'utilisateur ");
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL)){

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getId());

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                logger.info("Utilisateur modifié avec succès : {}", user.getUsername());
            }
            return true;


        }catch (SQLException e){
            logger.error("Erreur lors de modification de l'utilisateur : {}", user.getUsername());
            return false;
        }
    }


    // Supprimer un utilisateur
    @Override
    public boolean deleteUserById(int id) {
        String deleteUserSQL = "DELETE FROM users WHERE id = ?";
        logger.info("Suppression de l'utilisateur");

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)){

            preparedStatement.setInt(1, id);

            // Exécuter la requête
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Utilisateur supprimé avec succès :");
            }
            return true; // Retourne true si l'utilisateur a été modifier

        }catch (SQLException e){
            logger.error("Erreur lors de la suppression de l'utilisateur : ", e);
            return false;
        }

    }


}


