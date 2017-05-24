package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.HabitDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.service.HabitService;

/**
 * @Author Quentin
 */
public class HabitServiceImpl implements HabitService{

    private HabitDao habitDao;

    public HabitServiceImpl() {
        habitDao = new HabitDao();
    }

    @Override
    public Habit findHabitById(long id) {
        return habitDao.findHabitById(id);
    }

    @Override
    public void createHabit(String name) {
        habitDao.createHabit(name);
    }
}
