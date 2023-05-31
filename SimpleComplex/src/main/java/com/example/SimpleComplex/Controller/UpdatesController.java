package com.example.SimpleComplex.Controller;


import com.example.SimpleComplex.Records.WeeklyUpdate;
import com.example.SimpleComplex.Repository.Updates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/updates")
public class UpdatesController {

    @Autowired
    private Updates updates;

    @GetMapping("")
    public Iterable<WeeklyUpdate> displayAll() {
        return updates.findAll();
    }

    @GetMapping("/{id}")
    public Optional<WeeklyUpdate> findByUserId(@PathVariable int id) {
        return updates.findById(id);
    }

    @PostMapping("/save")
    public void updateUserInfo(@RequestBody WeeklyUpdate update) {
        WeeklyUpdate existingUpdate = updates.findById(update.getId()).orElse(null);
        existingUpdate = update;
        updates.save(existingUpdate);
    }
}
