package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      //Добавил bean-зависимости через setter класса User, теперь объект Car создается автоматически
      User user1 = (User)context.getBean("user");
      //Поля заполняются через сеттеры
      user1.setFirstName("name1");
      user1.setLastName("lastname1");
      user1.setEmail("mail@mail.ru");
      user1.getCar().setUser(user1);
      user1.getCar().setModel("model1");
      user1.getCar().setSeries(1);
      userService.add(user1);

      User user2 = (User)context.getBean("user");
      user2.setFirstName("name2");
      user2.setLastName("lastname2");
      user2.setEmail("mail@mail.ru");
      user2.getCar().setUser(user2);
      user2.getCar().setModel("model2");
      user2.getCar().setSeries(2);
      userService.add(user2);

      User user3 = (User)context.getBean("user");
      user3.setFirstName("name3");
      user3.setLastName("lastname3");
      user3.setEmail("mail@mail.ru");
      user3.getCar().setUser(user3);
      user3.getCar().setModel("model3");
      user3.getCar().setSeries(3);
      userService.add(user3);

      //переименовал метод в getListUsers, такое название более очевидное
      List<User> users = userService.getListUsers();
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

      List<User> users3 = userService.getListUsers("model1", 1);
      System.out.println(users3.size());
      for (User user : users3) {
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
