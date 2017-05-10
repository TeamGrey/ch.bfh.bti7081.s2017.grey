package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Role;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Data access object for {@link Role} Entity
 * @Author Quentin
 */
public class RoleDao {
    public static Role getRoleById(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CRM" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Role role = entitymanager.find( Role.class, id );
        entitymanager.close();
        emfactory.close();
        return role;
    }


    public static void createRole(String name) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CRM");

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();

        Role role = new Role();
        role.setName(name);
        role.setCreated(new Timestamp(instant.toEpochMilli()));
        role.setChanged(new Timestamp(instant.toEpochMilli()));

        entitymanager.persist(role);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();

    }
}
