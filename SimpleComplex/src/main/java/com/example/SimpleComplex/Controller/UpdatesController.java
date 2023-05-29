package com.example.SimpleComplex.Controller;


import com.example.SimpleComplex.Records.WeeklyUpdate;
import com.example.SimpleComplex.Repository.Updates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/updates")
public class UpdatesController {
    private final Updates updates;

    public UpdatesController(Updates updates) {
        this.updates = updates;
    }

    @GetMapping("")
    public Updates displayAll() {
        return updates;
    }

    @GetMapping("/{id}")
    public Optional<Stream<WeeklyUpdate>> findByUserId(@PathVariable int id) {
        return updates.getByUserId(id);
    }
}
