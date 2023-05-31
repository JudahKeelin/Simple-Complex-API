package com.example.SimpleComplex.Repository;
import com.example.SimpleComplex.Records.WeeklyUpdate;
import org.springframework.data.repository.CrudRepository;

public interface Updates extends CrudRepository<WeeklyUpdate, Integer> {
}
