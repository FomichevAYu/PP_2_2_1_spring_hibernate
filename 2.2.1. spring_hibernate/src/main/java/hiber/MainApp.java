package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("model1", 1);
      User user1 = new User("User1", "Lastname1", "mail1@mail.ru");
      Car car2 = new Car("model2", 2);
      User user2 = new User("User2", "Lastname2", "mail2@mail.ru");
      Car car3 = new Car("model3", 3);
      User user3 = new User("User3", "Lastname3", "mail3@mail.ru");
      Car car4 = new Car("model4", 4);
      User user4 = new User("User4", "Lastname4", "mail4@mail.ru");


      car1.setUser(user1);
      userService.add(user1, car1);
      car2.setUser(user2);
      userService.add(user2, car2);
      car3.setUser(user3);
      userService.add(user3, car3);
      car4.setUser(user4);
      userService.add(user4, car4);

      List<User> users = userService.listUsers();
      System.out.println(users.size());
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model car = "+user.getCar().getModel());
         System.out.println("Series car = "+user.getCar().getSeries());
         System.out.println();
      }


      context.close();
   }
}
