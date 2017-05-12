package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Data access object for {@link Staff} Entity
 *
 * @Author Quentin.
 */
public class StaffDao {

    public Staff getStaffByLogin(String login) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Staff> criteriaQuery = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> staff = criteriaQuery.from(Staff.class);
        criteriaQuery.select(staff).where(criteriaBuilder.equal(staff.get("login"), login));

        TypedQuery<Staff> query = entityManager.createQuery(criteriaQuery);
        Staff result = query.getSingleResult();
        return result;
    }

    public void createStaff(String firstname, String lastname, String login, String pwhash, Role role) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();

        Staff staff = new Staff();
        staff.setFirstname(firstname);
        staff.setLastname(lastname);
        staff.setLogin(login);
        staff.setPwhash(pwhash);
        staff.setChanged(new Timestamp(instant.toEpochMilli()));
        staff.setCreated(new Timestamp(instant.toEpochMilli()));
        staff.setRoles(role);

        entitymanager.persist(staff);
        entitymanager.getTransaction().commit();

    }

    public void removeStaff(int id) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction( ).begin( );

        Staff staff = entitymanager.find( Staff.class, id );
        entitymanager.remove(staff);
        entitymanager.getTransaction( ).commit( );
        entitymanager.close( );
    }
}
