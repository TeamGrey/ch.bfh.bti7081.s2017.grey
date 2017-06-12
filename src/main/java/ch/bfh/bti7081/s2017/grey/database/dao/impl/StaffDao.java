package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by gabor on 29/05/17.
 */
public class StaffDao extends GenericDaoImpl<Staff> implements GenericDao<Staff> {

  public StaffDao(EntityManager em) {
    super(em);
  }

  public Staff getStaffByLogin(String login) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Staff> criteriaQuery = criteriaBuilder.createQuery(Staff.class);
    Root<Staff> staff = criteriaQuery.from(Staff.class);
    criteriaQuery.select(staff).where(criteriaBuilder.equal(staff.get("login"), login));

    TypedQuery<Staff> query = em.createQuery(criteriaQuery);
    return query.getSingleResult();
  }
}
