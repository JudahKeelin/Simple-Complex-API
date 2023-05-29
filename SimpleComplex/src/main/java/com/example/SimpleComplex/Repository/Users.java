package com.example.SimpleComplex.Repository;

import com.example.SimpleComplex.Records.UserInfo;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class Users {
//api key:AIzaSyAziaSeWiegbW8fAyYxJgLwoYj8WA8A5Sg

    private List<UserInfo> users;

    public Users(List<UserInfo> users) {
        this.users = users;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public Optional<UserInfo> getUserById(int id) {
        return Optional.ofNullable(users.stream().filter(user -> user.id() == id).findFirst().orElse(null));
    }

    public boolean addUser(UserInfo user) {
        return users.add(user);
    }

    public boolean deleteUserById(int id) {
        return users.removeIf(user -> user.id() == id);
    }

    public boolean updateUserById(int id, UserInfo user) {
        Optional<UserInfo> userToUpdate = getUserById(id);
        if (userToUpdate == null) {
            return false;
        }
        userToUpdate = Optional.ofNullable(user);
        return true;
    }

    @PostConstruct
    private void init() {
        users.add(new UserInfo(1, "John", "Doe", "MyComplex", "jbkeelin@yahoo.com", "password", "337-242-9975", "133 Greenfield Drive", "Carencro", "LA", "70520", "USA", 30.331060680966797, -92.0195344428699));
        users.add(new UserInfo(2, "James", "Bond", "SomeComplex", "jbkeelin@gmail.com", "password", "337-322-8208", "133 Greenfield Drive", "Carencro", "LA", "70520", "USA", 30.323368393744204, -92.03026974603779));

    }
}
