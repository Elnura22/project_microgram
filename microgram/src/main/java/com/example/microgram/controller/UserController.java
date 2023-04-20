package com.example.microgram.controller;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserService service;


    @GetMapping("/getList")
    public ResponseEntity<List<UserDTO>> users(){
        return new ResponseEntity<>(service.getListOfUsers(), HttpStatus.OK);
    }


    @GetMapping("/userByName")
    public ResponseEntity<List<UserDTO>> userByName(@RequestParam String name){
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/userByEmail")
    public ResponseEntity<List<UserDTO>> userByEmail(@RequestParam String email){
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }


    @GetMapping("/userByAccount")
    public ResponseEntity<List<UserDTO>> userByAccount(@RequestParam String account){
        return new ResponseEntity<>(service.findByAccount(account), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody UserDTO userDTO, @RequestParam String password){
        if (service.userExists(userDTO.getName())) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.registerNewUser(userDTO, password));
    }

//    @GetMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
//        if (service.checkUserForLogin(email, password)){
//            return ResponseEntity.status(HttpStatus.OK).body("Successful login!");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You entered invalid email or password, try again.");
//    }

}
