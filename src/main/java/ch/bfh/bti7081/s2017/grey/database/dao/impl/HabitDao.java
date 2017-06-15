package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import javax.persistence.EntityManager;

/**
 * Created by gabor on 29/05/17.
 */
public class HabitDao extends GenericDaoImpl<Habit> implements GenericDao<Habit> {

  public HabitDao(EntityManager em) {
    super(em);
  }
}
