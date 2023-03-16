package com.example.microgram.controller;

import com.example.microgram.entity.User;
import com.example.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @GetMapping("/connect")
    public ResponseEntity<String> connection() {
        return new ResponseEntity<>(service.connect(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(){
        return  new ResponseEntity<>(service.shouldCreateTable(), HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<String> select(){
        return new ResponseEntity<>(service.shouldSelectData(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users(){
        return new ResponseEntity<>(service.getListUsers(), HttpStatus.OK);
    }

    @GetMapping("/userByName")
    public ResponseEntity<List<User>> userByName(@RequestParam String name){
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/userByEmail")
    public ResponseEntity<List<User>> userByEmail(@RequestParam String email){
        return new ResponseEntity<>(service.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/userByAccount")
    public ResponseEntity<List<User>> userByAccount(@RequestParam String account){
        return new ResponseEntity<>(service.getByAccount(account), HttpStatus.OK);
    }
}
