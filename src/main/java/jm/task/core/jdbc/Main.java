package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("1", "1", (byte)15);
        userService.saveUser("2", "2", (byte)25);
        userService.saveUser("3", "3", (byte)35);
        userService.saveUser("4", "4", (byte)45);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
}}
