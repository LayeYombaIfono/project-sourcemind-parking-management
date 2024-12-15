package services;

import models.User;

import java.util.List;

public interface UserServiceInterface {

    boolean registerUser(User user); //Methode pour ajouter un utisateur

    List<User> getAllUsers(boolean includePasswords); //Recuperer tous les utilisateurs

    User getUserById(int id); //Recuperer l'utilisateur par id

    boolean updateUser(User user); // Mise à jour de l'utilisateur

    boolean deleteUserById(int id); //Supprimer l'utilisateur par id


}
