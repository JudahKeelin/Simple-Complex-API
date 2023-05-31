package com.example.SimpleComplex.Controller;

import com.example.SimpleComplex.Records.UserInfo;
import com.example.SimpleComplex.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserInfoRepository userInfo;


    @GetMapping("")
    public Iterable<UserInfo> displayAll() {
        return userInfo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserInfo> findById(@PathVariable int id) {
        return userInfo.findById(id);
    }


    @PostMapping("/save")
    public void updateUserInfo(@RequestBody UserInfo user) {
        UserInfo existingUser = userInfo.findById(user.getId()).orElse(null);
        existingUser = user;
        userInfo.save(existingUser);
    }
}
