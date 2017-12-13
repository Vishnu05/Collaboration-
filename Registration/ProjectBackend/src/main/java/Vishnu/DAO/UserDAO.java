package Vishnu.DAO;

import Vishnu.Model.User;
 ;
public interface UserDAO {
void registerUser(User user);
boolean isEmailValid(String email);
boolean isUsernameValid(String username);
User login(User user);
void updateUser(User user);
User getUserByUsername(String username);

}
