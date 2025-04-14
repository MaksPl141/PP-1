package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 25);
        userService.saveUser("Oleg", "Petrov", (byte) 30);
        userService.saveUser("Maxim", "Semenov", (byte) 28);
        userService.saveUser("Igor", "Severov", (byte) 35);

        List<User> allUsers = userService.getAllUsers();
        System.out.println("\nСписок всех пользователей:");
        allUsers.forEach(System.out::println);

        userService.cleanUsersTable();
        System.out.println("\nТаблица пользователей очищена");

        userService.dropUsersTable();
        System.out.println("Таблица пользователей удалена");
    }
}

