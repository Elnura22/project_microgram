package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getDataSourceConn() {
        DataSource dataSource = getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            if (connection.isValid(1)) {
                return "All is ok";
            } else {
                throw new SQLException();
            }

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private DataSource getDataSource() {
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

    public List<UserDTO> getListOfUsers() {
        var userList = userDao.getUsers();
        return userList.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public boolean userExists(String name) {
        List<UserDTO> list = getListOfUsers();
        for (var user : list) {
            if (user.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUserForLogin(String email, String password) {
        var list = userDao.getUsers();
        for (var user : list) {
            if (user.getEmail().equalsIgnoreCase(email) &&
                    user.getPassword().equalsIgnoreCase(password)) {
                return true;
            }
        }
        return false;
    }


    public List<UserDTO> findByName(String name) {
        var userList = userDao.findUserByName(name);
        return userList.stream().map(UserDTO::from).collect(Collectors.toList());
    }


    public List<UserDTO> findByEmail(String email) {
        var userList = userDao.findUserByEmail(email);
        return userList.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public List<UserDTO> findByAccount(String account) {
        var userList = userDao.findUserByAccount(account);
        return userList.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public UserDTO registerNewUser(UserDTO userDTO, String password) {
        var user = User.builder()
                .name(userDTO.getName())
                .account(userDTO.getAccount())
                .email(userDTO.getEmail())
                .password(password)
                .counterPublication(0)
                .counterFollower(0)
                .counterFollowing(0)
                .build();
        userDao.save(user);
        return UserDTO.from(userDao.findUserByEmail(user.getEmail()).get());
    }
}
