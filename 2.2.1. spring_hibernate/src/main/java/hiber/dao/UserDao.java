package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   User saveUser();
   void add(User user);
   List<User> listUsers();
}
