package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Role;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Data access object for {@link Role} Entity
 * @Author Quentin
 */
public class RoleDao {

    private EntityManager entityManager;

    public RoleDao () {
        entityManager = EntityManagerSingleton.getInstance();
    }

    public  Role getRoleById(long id) {
        Role role = entityManager.find( Role.class, id );
        return role;
    }


    public void createRole(String name) {
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();

        Role role = new Role();
        role.setName(name);
        role.setCreated(new Timestamp(instant.toEpochMilli()));
        role.setChanged(new Timestamp(instant.toEpochMilli()));

        entityManager.persist(role);
        entityManager.getTransaction().commit();
    }
}
