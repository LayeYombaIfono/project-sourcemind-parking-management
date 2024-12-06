package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    //Méthode pour hasher un mot de passe
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Méthode pour vérifier un mot de passse en clair avec son hash
    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
