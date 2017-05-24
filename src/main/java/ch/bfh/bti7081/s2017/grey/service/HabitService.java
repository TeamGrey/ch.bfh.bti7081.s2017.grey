package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Habit;

/**
 * @Author Quentin
 */
public interface HabitService {
    Habit findHabitById(long id);

    void createHabit(String name);
}
