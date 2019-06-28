package com.mrbook.dao;

import com.mrbook.entity.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecordDao {
    private static RecordDao recordDao = new RecordDao();
    private Connection connection = null;

    public static RecordDao getInstance() {
        return recordDao;
    }

    private RecordDao() {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/lottery";
            connection = DriverManager.getConnection(url, "root", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveRecord(String award, int num, Date date) {
        String insert = "insert into record (award, num, time) values (?, ?, ?)";
        try (
                PreparedStatement statement = connection.prepareStatement(insert);
        ) {
            Timestamp time = new Timestamp(date.getTime());
            statement.setString(1, award);
            statement.setInt(2, num);
            statement.setTimestamp(3, time, Calendar.getInstance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getAll() {
        String query = "select * from record";
        List<Record> results = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setAward(rs.getString("award"));
                record.setNum(rs.getInt("num"));
                Timestamp time = rs.getTimestamp("time", Calendar.getInstance());
                record.setDate(new Date(time.getTime()));
                results.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
