package com.example.microgram.controller;

import com.example.microgram.dto.UserDTO;
import com.example.microgram.dto.UserDTOSecond;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserService service;


    @GetMapping("/getList")
    public ResponseEntity<List<UserDTO>> users() {
        return new ResponseEntity<>(service.getListOfUsers(), HttpStatus.OK);
    }


    @GetMapping("/userByName")
    public ResponseEntity<List<UserDTO>> userByName(@RequestParam String name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/userByEmail")
    public ResponseEntity<List<UserDTO>> userByEmail(@RequestParam String email) {
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }


    @GetMapping("/userByAccount")
    public ResponseEntity<List<UserDTO>> userByAccount(@RequestParam String account) {
        return new ResponseEntity<>(service.findByAccount(account), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(/*@RequestBody UserDTO userDTO,*/
            @RequestParam("name") String name,
            @RequestParam("accountName") String accountName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
//        if (service.userExists(userDTO.getName())) {
//            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok(service.registerNewUser(userDTO, password));
        UserDTOSecond userDTOSecond = UserDTOSecond.builder()
                .name(name)
                .account(accountName)
                .email(email)
                .build();

        if (service.userExists(email)) {
            return ResponseEntity.badRequest().build();
        } else return ResponseEntity.ok((service.registerNewUser(userDTOSecond, password)));
    }


//    @GetMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String email,
//                                   @RequestParam String password) {
//        if (service.checkUs-erForLogin(email, password)) {
//            return ResponseEntity.status(HttpStatus.OK).body("Successful login!");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You entered invalid email or password, try again.");
//    }

}
