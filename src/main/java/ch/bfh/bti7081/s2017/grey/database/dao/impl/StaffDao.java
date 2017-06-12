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
    /**
     * Find a staff by login name
     * @param login Login name
     * @return Staff
     */
    public Staff getStaffByLogin(String login) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Staff> criteriaQuery = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> staff = criteriaQuery.from(Staff.class);
        criteriaQuery.select(staff).where(criteriaBuilder.equal(staff.get("login"), login));

        TypedQuery<Staff> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
