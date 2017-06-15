package ch.bfh.bti7081.s2017.grey.database.dao;

import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface GenericDao<T> {

  T create(T t);

  /**
   * Delete an entity from the database
   * @param id Id of the entity to be deleted
   */
  void delete(Object id);

  /**
   * Find an entity by id
   * @param id Id of the entity
   * @return Entity if found
   */
  T find(Object id);

  T update(T t);

  /**
   * Return all entities of this type from the database
   * @return List of entities
   */
  List<T> findAll();
}
