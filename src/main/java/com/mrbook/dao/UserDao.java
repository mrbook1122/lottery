package com.mrbook.dao;

import com.mrbook.entity.Result;
import com.mrbook.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao userDao = new UserDao();
    private static String url = "jdbc:mysql://localhost/lottery";
    private Connection connection = null;

    private UserDao() {
        init();
    }

    private void init() {
        String url = "jdbc:mysql://localhost/lottery";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getInstance() {
        return userDao;
    }

    public void save(User user) {
        if (user != null) {
            String insert = "insert into user (name, number, coming) values (?, ?, ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(insert);
                statement.setString(1, user.getName());
                statement.setString(2, user.getNumber());
                statement.setBoolean(3, user.isComing());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from user";
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setComing(rs.getBoolean("coming"));
                user.setName(rs.getString("name"));
                user.setNumber(rs.getString("number"));
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getUsers() {
        String query = "select * from user where coming";
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setComing(rs.getBoolean("coming"));
                user.setName(rs.getString("name"));
                user.setNumber(rs.getString("number"));
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteByNumber(String number) {
        Statement statement = null;
        String delete = "delete from user where number = '" + number + "'";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(delete);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByNumber(String number) {
        Statement statement = null;
        String query = "select * from user where number = '" + number + "'";
        User user = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setComing(rs.getBoolean("coming"));
                user.setName(rs.getString("name"));
                user.setNumber(rs.getString("number"));
                return user;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(Result result) {
        String update = "update user set coming = " + result.getCode();
        if (!result.getMsg().equals("all"))
            update += " where number = '" + result.getMsg() + "'";
        try (
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
