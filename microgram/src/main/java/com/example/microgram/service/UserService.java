package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

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
        password = passwordEncoder.encode(password);
        var user = User.builder()
                .name(userDTO.getName())
                .account(userDTO.getAccount())
                .email(userDTO.getEmail())
                .password(password)
                .counterPublication(0)
                .counterFollower(0)
                .counterFollowing(0)
                .role(userDTO.getRole())
                .enabled(userDTO.isEnabled())
                .build();
        userDao.save(user);
        return UserDTO.from(user);
    }

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optUser = userDao.findUserByName(name);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found");
        }
        return optUser.get();
    }
}
