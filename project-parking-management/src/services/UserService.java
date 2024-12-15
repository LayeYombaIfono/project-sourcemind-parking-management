package services;


import database.DatabaseConnection;
import models.User;
import org.slf4j.*;
import services.interfaces.UserServiceInterface;
import utils.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Ajouter un utilisateur
    @Override
    public boolean registerUser(User user) {
        validateUser(user);
        String registerUserSQL = "INSERT INTO User (username, email, password, role) VALUES (?, ?, ?, ?)";
        logger.info("Tentative d'ajout d'un utilisateur : {}", user.getUsername());

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(registerUserSQL)){

            //Hasher le mot de passe
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, user.getRole());


            return true; // Retourne true si l'utilisateur a été enregistré

        }catch (SQLException e){
            if (e.getMessage().contains("Duplicate entry")){
                throw new IllegalArgumentException("L'utilisateur avec cet e-mail ou ce nom d'utilisateur existe déjà.");
            }
            throw new RuntimeException("Erreur lors de l'ajout de l'utilisateur.", e);
        }

    }

    // Récupérer un utilisateur par son id
    @Override
    public User getUserById(int id) {

        String getUserSQL = "SELECT * FROM User WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getUserSQL)){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
            }else {
                throw new IllegalArgumentException("Aucun utilisateur trouvé avec l'ID " + id + ".");
            }

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur.", e);
        }

    }

    // Recuperer tous les utilisateurs
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM User";

        try(Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsersSQL)){

            //Boucle pour parcourir la liste des utilisateurs
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                ));

            }

        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs.", e);
        }

        return users;
    }

    // Modifier les information de l'utilisateur
    @Override
    public boolean updateUser(User user) {
        validateUser(user);
        String updateUserSQL = "UPDATE User SET username = ?, email = ?, role = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateUserSQL)){

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getId());

            return true;
        }catch (SQLException e){
            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur.", e);
        }
    }

    // Supprimer un utilisateur
    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM User WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur.", e);
        }
    }

    //Validation des informations de l'utilisateur
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Le nom d'utilisateur est obligatoire.");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Adresse e-mail invalide.");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères.");
        }
    }

}


