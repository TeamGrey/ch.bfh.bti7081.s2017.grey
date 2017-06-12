package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.HabitDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.service.HabitService;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class HabitServiceImpl implements HabitService{

    private HabitDao dao;

    public HabitServiceImpl() {
        dao = new HabitDao();
    }

    /**
     * Finds a habit by it's id
     * @param id Id of the habit
     * @return Habit if found
     */
    @Override
    public Habit findHabitById(long id) {
        return dao.find(id);
    }

    /**
     * Creates a new habit
     * @param name Name of the habit
     */
    @Override
    public void createHabit(String name) {
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Habit habit = new Habit();
        habit.setName(name);
        habit.setCreated(timestamp);
        habit.setChanged(timestamp);

        dao.create(habit);
    }
}
