package com.example.microgram.controller;


import com.example.microgram.dto.UserDTO;
import com.example.microgram.entity.User;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserService service;
//    @GetMapping("/connect")
//    public ResponseEntity<String> connection() {
//        return new ResponseEntity<>(service.connect(), HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> create(){
//        return new ResponseEntity<>(service.shouldCreateTable(), HttpStatus.OK);
//    }
//
//    @GetMapping("/select")
//    public ResponseEntity<String> select(){
//        return new ResponseEntity<>(service.shouldSelectData(), HttpStatus.OK);
//    }

    @GetMapping("/getList")
    public ResponseEntity<List<UserDTO>> users(){
        return new ResponseEntity<>(service.getListOfUsers(), HttpStatus.OK);
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, String invalid) {
        if (invalid != null)
            model.addAttribute("invalid", "Your username and password is invalid.");
        return "loginForm";
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        if (service.checkUserForLogin(email, password)){
            return ResponseEntity.status(HttpStatus.OK).body("Successful login!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You entered invalid email or password, try again.");
    }

}
