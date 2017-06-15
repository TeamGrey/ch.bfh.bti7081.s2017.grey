package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import java.util.List;

/**
 * @Author Quentin
 */
public interface HabitService {

  /**
   * Finds a habit by it's id
   *
   * @param id Id of the habit
   * @return Habit if found
   */
  Habit findHabitById(long id);

  /**
   * Creates a new habit
   *
   * @param name Name of the habit
   */
  void createHabit(String name);

  List<Habit> getAllHabits();
}
