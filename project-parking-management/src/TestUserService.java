import models.User;
import services.UserService;

import java.util.List;

public class TestUserService {
    public static void main(String[] args) {

        UserService userService  = new UserService();
       User user1 = new User( "IFONO", "ifono@gmail.com", "password1234", "user");
       User user2 = new User( "jean IFONO", "jean12@gmail.com", "password1234jean", "user");

       boolean isResgitered = userService.registerUser(user1);
       boolean isResgistered2 = userService.registerUser(user2);


       if (isResgitered){
           //System.out.println("Utilisateur enregistrer avec succes " + user1.getUsername());
           System.out.println("Utilisateur enregistrer avec succes " + user2.getUsername());
       }else {
           System.out.println("Echec");
       }



        //boolean userAdded2 = userService.addUser("Ifono", "ifono12@gmail.com", "1991", "Admin");
        //System.out.println("L'utilisateur ajoute avec succes !" + userAdded);

        // Authentifier un utilisateur
        //boolean authenticated = userService.authenticate("ifono12@gmail.com", "1991");

        //System.out.println("Authentification reussie : " + authenticated);

        // Tester un mot de passe incorrect
        //boolean failedAuth = userService.authenticate("johndoe@gmail.com", "14578962@ghgjg");
        //System.out.println("Authentification echoue : " + failedAuth);

        // Recuperer tous les utilisateurs
//        List<User> users = userService.getAllUser();
//        System.out.println("liste des utilisateurs");
//        for (User user : users){
//            System.out.println("Nom : " + user.getUsername()  + "\nEmail : " + user.getEmail());
//        }

        //Recuperer un utilisateur
//        User user = userService.getUserById(1);
//        if (user != null){
//            System.out.println("Utilisateur trouver : " + user.getUsername());
//        }

        // Supprimer un utilisateur
//        boolean userDeleted = userService.deleteUser(1);
//        System.out.println("Utilisateur supprime : " + userDeleted);
    }
}
