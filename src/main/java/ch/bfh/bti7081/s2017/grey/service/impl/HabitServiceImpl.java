package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.HabitDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.service.HabitService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class HabitServiceImpl implements HabitService {

  private HabitDao dao;

  public HabitServiceImpl(EntityManager em) {
    dao = new HabitDao(em);
  }

  /**
   * @see HabitService#findHabitById(long)
   */
  @Override
  public Habit findHabitById(long id) {
    return dao.find(id);
  }

  /**
   * @see HabitService#createHabit(String)
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

  @Override
  public List<Habit> getAllHabits() {
    return dao.findAll();
  }
}
