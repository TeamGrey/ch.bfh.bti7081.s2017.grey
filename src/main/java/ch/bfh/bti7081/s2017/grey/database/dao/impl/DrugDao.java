package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import javax.persistence.EntityManager;

/**
 * Created by gabor on 29/05/17.
 */
public class DrugDao extends GenericDaoImpl<Drug> implements GenericDao<Drug> {

  public DrugDao(EntityManager em) {
    super(em);
  }

}
