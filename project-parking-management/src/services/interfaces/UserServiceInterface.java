package services.interfaces;

import models.User;

import java.util.List;

public interface UserServiceInterface {

    boolean registerUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    boolean updateUser(User user);
    void deleteUser(int id);


}
