package com.example.SimpleComplex.Repository;

import com.example.SimpleComplex.Records.UserInfo;

import java.util.List;

public class Users {
    private List<UserInfo> users;

    public Users(List<UserInfo> users) {
        this.users = users;
    }

    public void addUsers(UserInfo user) {
        users.add(user);
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    //unfinished
    public List<UserInfo> getUsersByProximity(int id) {
        return users;
    }

    public Record getUserById(int id) {
        return users.get(id);
    }
}
