package com.example.SimpleComplex.Controller;

import com.example.SimpleComplex.Records.UserInfo;
import com.example.SimpleComplex.Records.WeeklyUpdate;
import com.example.SimpleComplex.Repository.Updates;
import com.example.SimpleComplex.Repository.UserInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MainController {

    private final UserInfoRepository userInfoRepository;

    private final Updates updatesRepository;

    public MainController(UserInfoRepository userInfoRepository, Updates updatesRepository) {
        this.userInfoRepository = userInfoRepository;
        this.updatesRepository = updatesRepository;
    }

    @GetMapping("/updates")
    public Iterable<WeeklyUpdate> displayAll() {
        return updatesRepository.findAll();
    }

    @GetMapping("/updates/{id}")
    public Collection<? extends WeeklyUpdate> findByUserId(@PathVariable int id) {
        return updatesRepository.findAllByUserId(id);
    }

    @PostMapping("/updates/add")
    public void addNewUpdate(@RequestBody WeeklyUpdate update) {
        updatesRepository.save(update);
    }

    @PostMapping("/updates/save")
    public void updateUserInfo(@RequestBody WeeklyUpdate update) {
        WeeklyUpdate existingUpdate;
        existingUpdate = update;
        updatesRepository.save(existingUpdate);
    }

    @GetMapping("/users/find")
    public int getUserId(@RequestParam String email, @RequestParam String password) {
        UserInfo user = userInfoRepository.findByEmailAndPassword(email.toLowerCase(), password);
        return user.getId();
    }

    @GetMapping("/users/home/{id}")
    public List<WeeklyUpdate> getHomePage(@PathVariable int id) {
        List<WeeklyUpdate> updates = new ArrayList<>();
        UserInfo user = userInfoRepository.findById(id).orElse(null);
        if (user != null) {
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
            double distance = getDistance(user.getLatitude(), user.getLongitude(), neighbor.getLatitude(), neighbor.getLongitude());
            if (distance < 5) {
                user.setNeighborhood(user.getNeighborhood() + "," + neighbor.getId());
                neighbor.setNeighborhood(neighbor.getNeighborhood() + "," + user.getId());
            }
        }
        userInfoRepository.save(user);
    }

    @PostMapping("/users/save")
    public void updateUserInfo(@RequestBody UserInfo user) {
        UserInfo existingUser;
        existingUser = user;
        userInfoRepository.save(existingUser);
    }

    @GetMapping("/users/name/{id}")
    public String getUserName(@PathVariable int id) {
        UserInfo user = userInfoRepository.findById(id).orElse(null);
        if (user != null) {
            return user.getComplexName();
        }
        return null;
    }


    public double getDistance(double lat1, double long1, double lat2, double long2) {
        double distance;
        double lat = Math.toRadians(lat2-lat1);
        double lon = Math.toRadians(long2-long1);
        double a = Math.sin(lat/2) * Math.sin(lat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lon/2) * Math.sin(lon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        distance = 6371 * c;
        System.out.println(distance);
        return distance;
    }
}
