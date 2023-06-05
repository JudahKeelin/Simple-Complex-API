package com.example.SimpleComplex.Controller;

import com.example.SimpleComplex.Records.UserInfo;
import com.example.SimpleComplex.Records.WeeklyUpdate;
import com.example.SimpleComplex.Repository.Updates;
import com.example.SimpleComplex.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private Updates updatesRepository;

    @GetMapping("/updates")
    public Iterable<WeeklyUpdate> displayAll() {
        return updatesRepository.findAll();
    }

    @GetMapping("/updates/{id}")
    public Collection<? extends WeeklyUpdate> findByUserId(@PathVariable int id) {
        return updatesRepository.findAllByUserId(id);
    }

    @PostMapping("/updates/save")
    public void updateUserInfo(@RequestBody WeeklyUpdate update) {
        WeeklyUpdate existingUpdate = updatesRepository.findById(update.getId()).orElse(null);
        existingUpdate = update;
        updatesRepository.save(existingUpdate);
    }

    @GetMapping("/users/byEmail")
    public UserInfo findByEmail(String email) {
        System.out.println(userInfoRepository.findByEmail(email.toLowerCase()));
        return userInfoRepository.findByEmail(email.toLowerCase());
    }

    @GetMapping("/users/home")
    public List<WeeklyUpdate> getHomePage(@RequestParam String email, @RequestParam String password) {
        List<WeeklyUpdate> updates = new ArrayList<>();
        UserInfo user = userInfoRepository.findByEmail(email.toLowerCase());
        if (user.getPassword().equals(password)) {
            for (String neighbor : user.getNeighborhood().split(",")) {
                updates.addAll(updatesRepository.findAllByUserId(Integer.parseInt(neighbor)));
            }
        }
        return updates;
    }

    @PostMapping("/users/add")
    public void addNewUser(@RequestBody UserInfo user) {
        Iterable<UserInfo> potentialNeighbors = userInfoRepository.findAll();
        userInfoRepository.save(user);
        user.setNeighborhood(String.valueOf(user.getId()));
        for (UserInfo neighbor : potentialNeighbors) {
            Double distance = getDistance(user.getLatitude(), user.getLongitude(), neighbor.getLatitude(), neighbor.getLongitude());
            if (distance < 5) {
                user.setNeighborhood(user.getNeighborhood() + "," + neighbor.getId());
                neighbor.setNeighborhood(neighbor.getNeighborhood() + "," + user.getId());
            }
        }
        userInfoRepository.save(user);
    }

    @PostMapping("/users/save")
    public void updateUserInfo(@RequestBody UserInfo user) {
        UserInfo existingUser = userInfoRepository.findById(user.getId()).orElse(null);
        existingUser = user;
        userInfoRepository.save(existingUser);
    }


    public double getDistance(double lati1, double long1, double lati2, double long2) {
        double distance = 0;
        double lat = Math.toRadians(lati2-lati1);
        double lon = Math.toRadians(long2-long1);
        double a = Math.sin(lat/2) * Math.sin(lat/2) +
                Math.cos(Math.toRadians(lati1)) * Math.cos(Math.toRadians(lati2)) *
                        Math.sin(lon/2) * Math.sin(lon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = 6371 * c;
        System.out.println(distance);
        return distance;
    }
}
