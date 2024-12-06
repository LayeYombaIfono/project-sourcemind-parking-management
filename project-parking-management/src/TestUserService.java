import models.User;
import services.UserService;



public class TestUserService {
    public static void main(String[] args) {

       UserService userService  = new UserService();

    //Ajouter un utilisateur

        //User user1 = new User( "IFONO", "ifono@gmail.com", "password1234", "user");
        User user2 = new User( "jean IFONO", "jean12@gmail.com", "password1234jean", "user");

       boolean isResgitered = userService.registerUser(user2);
       //boolean isResgistered2 = userService.registerUser(user2);


       if (isResgitered){
           //System.out.println("Utilisateur enregistrer avec succes " + user1.getUsername());
           System.out.println("Utilisateur enregistrer avec succes " + user2.getUsername());
       }else {
           System.out.println("Echec");
       }




    }
}
