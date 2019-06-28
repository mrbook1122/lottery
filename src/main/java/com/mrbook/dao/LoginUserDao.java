package com.mrbook.dao;

import com.mrbook.entity.LoginUser;

import java.sql.*;

public class LoginUserDao {
    private static LoginUserDao loginUserDao = new LoginUserDao();
    private Connection connection = null;

    private LoginUserDao() {
        init();
    }

    public static LoginUserDao getLoginUserDao() {
        return loginUserDao;
    }

    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lottery",
                    "root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public LoginUser findByName(String name) {
        String query = "select * from login_user where name = ?";
        LoginUser loginUser = null;
        try (
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                loginUser = new LoginUser();
                loginUser.setId(rs.getInt("id"));
                loginUser.setName(rs.getString("name"));
                loginUser.setPass(rs.getString("pass"));
                loginUser.setPhone(rs.getString("phone"));
                loginUser.setGender(rs.getString("gender"));
                loginUser.setFileName(rs.getString("fileName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginUser;
    }

    public void save(LoginUser loginUser) {
        String insert = "insert into login_user (name, pass, phone, gender, fileName) values (?, ?, ?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(insert);
        ) {
            statement.setString(1, loginUser.getName());
            statement.setString(2, loginUser.getPass());
            statement.setString(3, loginUser.getPhone());
            statement.setString(4, loginUser.getGender());
            statement.setString(5, loginUser.getFileName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
