package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.HabitDao;
import ch.bfh.bti7081.s2017.grey.database.dao.impl.HabitDaoImpl;
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
        dao = new HabitDaoImpl();
    }

    @Override
    public Habit findHabitById(long id) {
        return dao.find(id);
    }

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
