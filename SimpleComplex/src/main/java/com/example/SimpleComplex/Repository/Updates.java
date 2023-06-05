package com.example.SimpleComplex.Repository;
import com.example.SimpleComplex.Records.WeeklyUpdate;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface Updates extends CrudRepository<WeeklyUpdate, Integer> {
    Collection<? extends WeeklyUpdate> findAllByUserId(int id);
}
