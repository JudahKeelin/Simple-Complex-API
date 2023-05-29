package com.example.SimpleComplex.Controller;

import com.example.SimpleComplex.Records.UserInfo;
import com.example.SimpleComplex.Repository.Updates;
import com.example.SimpleComplex.Repository.Users;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final Users users;

    public UserController(Users users) {
        this.users = users;
    }

    @GetMapping("")
    public Users displayAll() {
        return users;
    }

    @GetMapping("/{id}")
    public Optional<UserInfo> findById(@PathVariable int id) {
        return users.getUserById(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserInfo user) {
        users.addUser(user);
    }

}
