import models.User;
import services.UserService;

import java.util.List;

public class TestUserService {
    public static void main(String[] args) {

       UserService userService  = new UserService();

    //Ajouter un utilisateur
      /*
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
       */

        //Recuperer un utilisateur

    /*int userId = 1;
    User userById = userService.getUserById(userId);

    if (userById != null){
        System.out.println("Utilisateur trouver avec ID : " + "\nId: " +
                userById.getId() + "\nNom: " + userById.getUsername() + "\nEmail: "
                + userById.getEmail() + "\nRole: " + userById.getRole());
    }else {
        System.out.println("Aucun utilisateur trouvé avec l'ID " + userId);
    }*/

    //Recuperer tous les utilisateurs
        List<User> users = userService.getAllUsers();

        //Afficher les utilisateurs
        if (!users.isEmpty()){
            System.out.println("Liste des utilisateurs: ");

            for (User user : users){
                System.out.println("Nom: " + user.getUsername() + "\nEmail: " + user.getEmail()
                + "\nRole: " + user.getRole());
                System.out.println("<-------------------->");
            }
        }else {
            System.out.println("Aucun utilisateur trouvé");
        }

    }
}
