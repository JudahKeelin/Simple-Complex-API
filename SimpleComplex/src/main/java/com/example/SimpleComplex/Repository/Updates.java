package com.example.SimpleComplex.Repository;

import com.example.SimpleComplex.Records.WeeklyUpdate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class Updates {

    private final List<WeeklyUpdate> updates;

    public Updates(List<WeeklyUpdate> updates) {
        this.updates = updates;
    }

    public List<WeeklyUpdate> getUpdates() {
        return updates;
    }

    public Optional<Stream<WeeklyUpdate>> getByUserId(int userId) {
        return Optional.ofNullable(updates.stream().filter(user -> user.userId() == userId));
    }

    @PostConstruct
    public void init(){
        updates.add(new WeeklyUpdate(1, 1, LocalDate.now(), 100, 10, 5, 100.00, 150.00, 200.00));
        updates.add(new WeeklyUpdate(2, 2, LocalDate.now(), 100, 10, 5, 100.00, 150.00, 200.00));
        updates.add(new WeeklyUpdate(1, 3, LocalDate.now(), 100, 10, 5, 100.00, 150.00, 200.00));
        updates.add(new WeeklyUpdate(1, 4, LocalDate.now(), 100, 10, 5, 100.00, 150.00, 200.00));
        updates.add(new WeeklyUpdate(2, 5, LocalDate.now(), 100, 10, 5, 100.00, 150.00, 200.00));
    }
}
