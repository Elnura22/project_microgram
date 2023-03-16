package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.entity.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

//    public UserService() {
//        try {
//            init();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private Connection conn;
    @Autowired
    private UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=qwerty";
        return DriverManager.getConnection(url);
    }

    public String getDataSourceConn(){
        DataSource dataSource = getDataSource();
        try{Connection connection = dataSource.getConnection();
            if (connection.isValid(1)){
                return "All is ok";
            }else {
                throw new SQLException();
            }

        }catch (SQLException e){
            return e.getMessage();
        }
    }

    private DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername("postgres");
        config.setPassword("qwerty");
        config.setJdbcUrl("jdbc:postgresql://localhost/postgres?user=postgres&password=qwerty");
        return new HikariDataSource(config);
    }

    public String connect() {
        try {
            init();
            return "Connection to database was successful!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private void init() throws SQLException {
        conn = getNewConnection();
    }

    private int executeUpdate(String query) throws SQLException {
        Statement statement = conn.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }


    private void createCustomerTable() throws SQLException {
        String customerTableQuery = "CREATE TABLE customers " +
                "(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
        String customerEntryQuery = "INSERT INTO customers " +
                "VALUES (73, 'Brian', 33)";
        executeUpdate(customerTableQuery);
        executeUpdate(customerEntryQuery);
    }

    public String shouldCreateTable() {
        try {
            createCustomerTable();
            conn.createStatement().execute("select * from customers");
            return "All is OK";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String shouldSelectData() {
        try {
            createCustomerTable();
            String query = "select * from customers where name = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "Brian");

            if (ps.execute()) {
//                return "All is OK";
                ResultSet resultSet = ps.getResultSet();
                resultSet.next();
                int age = resultSet.getInt("age");
                return String.format("Age %s ", age);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<User> getListUsers(){
        return userDao.getUsers();
    }
    public List<User> getByName(String name){
        return userDao.findUserByName(name);
    }

    public List<User> getByEmail(String email){
        return userDao.findUserByEmail(email);
    }

    public List<User> getByAccount(String account){
        return userDao.findUserByAccount(account);
    }
}
