package tests;

import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.UserService;
import services.interfaces.UserServiceInterface;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUserService {
//    private UserServiceInterface userService;
//   private boolean includePasswords;
//
//    @Before
//    public void setUp(){
//        userService = new UserService();
//    }
//
//    @Test
//    public void testAddUserWithHashedPassword(){
//        User user = new User("testuser", "testuser@example.com", "testpassword", "user");
//        boolean result = userService.registerUser(user);
//       Assert.assertTrue("L'utilisateur devrait être ajouté avec succès", result);
//
//    }
//
//
//
//   @Test
//    public void testGetAllUsers (){
//       List<User> users = userService.getAllUsers();
//       Assert.assertNotNull("La liste des utilisateurs ne devrait pas être nulle", users);
//       Assert.assertFalse("La liste des utilisateurs ne devrait pas être vide", users.isEmpty());
//
//       System.out.println(users.toString());
//    }
//
//  @Test
//    public  void testGetUserById(){
//        User user = new User("userbyid", "userbyid@example.com", "password123", "admin");
//        userService.registerUser(user);
//
//        // Récupérer l'utilisateur ajouté
//        User fetchedUser = userService.getAllUsers()
//                .stream()
//                .filter(u -> u.getUsername().equals("userbyid"))
//                .findFirst()
//                .orElse(null);
//        Assert.assertNotNull("L'utilisateur récupéré ne doit pas être null", fetchedUser);
//        assertEquals("Les e-mail doivent correspond", "userbyid@example.com");
//    }
//
//   @Test
//    public void testUpdateUser() {
//
//       User user = new User("testuser", "newSecurePassword", "newemail@example.com", "admin");
//       boolean result = userService.updateUser(user);
//       Assert.assertTrue("L'utilisateur devrait être mis à jour avec succès", result);
//
//       Assert.assertNotNull("L'utilisateur mis à jour ne devrait pas être nul", user.getUsername());
//       assertEquals("Le rôle de l'utilisateur devrait être 'admin'", "admin", user.getRole());
//       assertEquals("L'email de l'utilisateur devrait être 'newemail@example.com'", "newemail@example.com", user.getEmail());
//        /*
//       User user = new User("userToUpdate", "userToUpdate@example.com", "initialpassword", "user");
//        userService.registerUser(user);
//
//        // Récupérer l'utlisateur pour modification
//        User fetchedUser = userService.getAllUsers(includePasswords)
//                .stream()
//                .filter(u -> u.getUsername().equals("userToUpdate"))
//                .findFirst()
//                .orElse(null);
//        assertNotNull("L'utilisateur récupéré ne doit pas être null", fetchedUser);
//        fetchedUser.setEmail("updatedemail@example.com");
//
//        // Mettre à jour l'utilisateur
//        boolean isUpdate = userService.updateUser(fetchedUser);
//        assertTrue("L'utilisateur doit être mis à jour avec succès", isUpdate);
//
//        // Vérifier la mise à jour
//        User updatedUser = userService.getUserById(fetchedUser.getId());
//        assertEquals("L'e-mail doit être mise à jour", "updatedemail@example.com", updatedUser.getEmail());
//
//         */
//    }
//
//    @Test
//    public void testDeleteUser() {
//        User user = new User("userToDelete", "userToDelete@example.com","password456", "user");
//        userService.registerUser(user);
//
//        // Récupérer l'utilisateur ajouté
//        User fetchedUser = userService.getAllUsers()
//                .stream()
//                .filter(u -> u.getUsername().equals("userToDelete"))
//                .findFirst()
//                .orElse(null);
//        Assert.assertNotNull("L'utilisateur récupéré ne doit pas être null", fetchedUser);
//
//        //Supprimer l'utilisateur
//        boolean isDeleted = userService.deleteUserById(fetchedUser.getId());
//        Assert.assertTrue("L'utilisateur doit être supprimé avec succès", isDeleted);
//
//        // Vérifier que l'utilisateur n'existe plus
//       //User deletedUser = userService.getUserById(fetchedUser.getId());
//       //assertNotNull("L'utilisateur supprimé ne devrait pas exister", deletedUser);
//
//    }
}
