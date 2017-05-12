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
    public static Role getRoleById(int id) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        Role role = entitymanager.find( Role.class, id );
        return role;
    }


    public static void createRole(String name) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();

        Role role = new Role();
        role.setName(name);
        role.setCreated(new Timestamp(instant.toEpochMilli()));
        role.setChanged(new Timestamp(instant.toEpochMilli()));

        entitymanager.persist(role);
        entitymanager.getTransaction().commit();
    }
}
