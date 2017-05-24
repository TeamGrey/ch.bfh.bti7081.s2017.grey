package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class HabitDao {

    private EntityManager entityManager;

    public HabitDao() {
        entityManager = EntityManagerSingleton.getInstance();
    }

    public Habit findHabitById(long id) {
        return entityManager.find(Habit.class, id);
    }

    public void createHabit(String name) {
        entityManager.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Habit habit = new Habit();
        habit.setName(name);
        habit.setCreated(timestamp);
        habit.setChanged(timestamp);

        entityManager.persist(habit);
        entityManager.getTransaction().commit();
    }
}
