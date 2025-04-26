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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car1 = new Car("Toyota Camry", 2020);
      Car car2 = new Car("Honda Accord", 2008);
      Car car3 = new Car("BMW X5", 2016);

      User userWithCar1 = new User("User1", "Lastname1", "user1@mail.ru");
      userWithCar1.setCar(car1);
      car1.setUser(userWithCar1);

      User userWithCar2 = new User("User2", "Lastname2", "user2@mail.ru");
      userWithCar2.setCar(car2);
      car2.setUser(userWithCar2);

      User userWithCar3 = new User("User3", "Lastname3", "user3@mail.ru");
      userWithCar3.setCar(car3);
      car3.setUser(userWithCar3);

      userService.add(userWithCar1);
      userService.add(userWithCar2);
      userService.add(userWithCar3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();

         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         } else {
            System.out.println("Машина не назначена");
         }
         System.out.println("---------------------");
      }

      System.out.println("\n=== Поиск владельца автомобиля ===");
      User foundUser = userService.findOwnerByCar("Honda Accord", 2008);
      if (foundUser != null) {
         System.out.println("Владелец Honda Accord:");
         System.out.println("Name: " + foundUser.getFirstName() + " " + foundUser.getLastName());
         System.out.println("Email: " + foundUser.getEmail());
      } else {
         System.out.println("Владелец не найден");
      }

      context.close();
   }
}

