package com.example.SimpleComplex.Repository;

import com.example.SimpleComplex.Records.WeeklyUpdate;

import java.util.Date;
import java.util.List;

public class Updates {
    private List<WeeklyUpdate> updates;

    public Updates(List<WeeklyUpdate> updates) {
        this.updates = updates;
    }

    public void addUpdate(WeeklyUpdate update) {
        updates.add(update);
    }


    public List<WeeklyUpdate> getUpdates() {
        return updates;
    }

    public List<WeeklyUpdate> getUpdatesById(int id) {
        return updates.get(id);
    }
}
