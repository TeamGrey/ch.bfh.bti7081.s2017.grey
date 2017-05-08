package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Data access object for Staff
 *
 * @Author Quentin.
 */
public class StaffDao {

    public Staff getStaffByLogin(String login) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CRM");
        EntityManager entityManager = emfactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Staff> criteriaQuery = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> staff = criteriaQuery.from(Staff.class);
        criteriaQuery.select(staff).where(criteriaBuilder.equal(staff.get("login"), login));

        TypedQuery<Staff> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
