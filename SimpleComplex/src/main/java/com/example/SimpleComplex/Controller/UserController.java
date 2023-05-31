package com.example.SimpleComplex.Controller;

import com.example.SimpleComplex.Records.UserInfo;
import com.example.SimpleComplex.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserInfoRepository userInfo;


    @GetMapping("")
    public List<String> displayAll() {
        Iterable users = userInfo.findAll();
        List<String> userComplex = null;
        for (UserInfo user : userInfo.findAll()) {
            userComplex.add(user.getComplexName());
        }
        return userComplex;
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody UserInfo user) {
        Iterable<UserInfo> potentialNeighbors = userInfo.findAll();
        userInfo.save(user);
        for (UserInfo neighbor : potentialNeighbors) {
            if (getDistance(user.getLatitude(), user.getLongitude(), neighbor.getLatitude(), neighbor.getLongitude()) < 5) {
                if (user.getNeighborhood() == null) {
                    user.setNeighborhood("");
                }
                user.setNeighborhood(user.getNeighborhood() + "," + neighbor.getId());
                if (neighbor.getNeighborhood() == null) {
                    neighbor.setNeighborhood("");
                }
                neighbor.setNeighborhood(neighbor.getNeighborhood() + "," + user.getId());
            }
        }
        userInfo.save(user);
    }

    @PostMapping("/save")
    public void updateUserInfo(@RequestBody UserInfo user) {
        UserInfo existingUser = userInfo.findById(user.getId()).orElse(null);
        existingUser = user;
        userInfo.save(existingUser);
    }


    public double getDistance(double lati1, double long1, double lati2, double long2) {
        double distance = 0;
        double lat1 = Math.toRadians(lati1);
        double lat2 = Math.toRadians(lati2);
        double longDiff = Math.toRadians(long2 - long1);
        double distance1 = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(longDiff);
        double distance2 = Math.acos(distance1);
        double distance3 = Math.toDegrees(distance2);
        distance = distance3 * 69.09;
        System.out.println(distance);
        return distance;
    }
}
