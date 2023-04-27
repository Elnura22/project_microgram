package com.example.microgram.service;

import com.example.microgram.dao.UserDao;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.dto.UserDTOSecond;
import com.example.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public boolean userExists(String email) {
        List<UserDTO> list = getListOfUsers();
        for (var user : list) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

//    public boolean checkUserForLogin(String email, String password) {
//        var list = userDao.getUsers();
//        for (var user : list) {
//            if (user.getEmail().equalsIgnoreCase(email) &&
//                    user.getPassword().equalsIgnoreCase(password)) {
//                return true;
//            }
//        }
//        return false;
//    }


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

    public UserDTO registerNewUser(/*UserDTO userDTO,*/
            UserDTOSecond userDTOSecond,
            String password) {
        password = passwordEncoder.encode(password);
        User user = User.builder()
                .name(userDTOSecond.getName())
                .account(userDTOSecond.getAccount())
                .email(userDTOSecond.getEmail())
                .password(password)
                .counterPublication(0L)
                .counterFollower(0L)
                .counterFollowing(0L)
                .role("user")
                .enabled(true)
                .build();
        userDao.save(user);
//        var user = User.builder()
//                .name(userDTO.getName())
//                .account(userDTO.getAccount())
//                .email(userDTO.getEmail())
//                .password(password)
//                .counterPublication(0L)
//                .counterFollower(0L)
//                .counterFollowing(0L)
//                .role(userDTO.getRole())
//                .enabled(userDTO.isEnabled())
//                .build();
//        userDao.save(user);
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
