package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("1", "1", (byte)15);
        userService.saveUser("2", "2", (byte)25);
        userService.saveUser("3", "3", (byte)35);
        userService.saveUser("4", "4", (byte)45);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
