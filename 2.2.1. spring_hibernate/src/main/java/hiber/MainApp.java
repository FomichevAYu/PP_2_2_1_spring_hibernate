package hiber;

import hiber.config.AppConfig;
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

        //Сущности user и car теперь не являются компонентами спринга
        User user1 = new User("name1", "lastname1", "email1@mail.ru");
        //зависимость через сеттер
        user1.setCar("model1", 1, user1);

        User user2 = new User("name2", "lastname2", "email2@mail.ru");
        user2.setCar("model2", 2, user2);

        User user3 = new User("name3", "lastname3", "email3@mail.ru");
        user3.setCar("model3", 3, user3);

        User user4 = new User("name4", "lastname4", "email4@mail.ru");
        user4.setCar("model4", 4, user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.getListUsers();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model car = " + user.getCar().getModel());
            System.out.println("Series car = " + user.getCar().getSeries());
            System.out.println();
        }

        List<User> users3 = userService.getListUsers("model1", 1);
        System.out.println(users3.size());
        for (User user : users3) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model car = " + user.getCar().getModel());
            System.out.println("Series car = " + user.getCar().getSeries());
            System.out.println();
        }


        context.close();
    }
}
