package ch.bfh.bti7081.s2017.grey.database.dao;

import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface GenericDao<T> {
    T create(T t);
    void delete(Object id);
    T find (Object id);
    T update(T t);
    List<T> findAll();
}
