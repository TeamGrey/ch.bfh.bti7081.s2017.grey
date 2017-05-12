package ch.bfh.bti7081.s2017.grey.database.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton implementation of the JPA EntityManager.
 *
 * @author Quentin
 * @version 12.05.2017
 */
public class EntityManagerSingleton {

    private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CRM");
    private static EntityManager entityManager = emFactory.createEntityManager();

    private EntityManagerSingleton(){
        // Singleton
    }

    public static EntityManager getInstance() {
        return entityManager;
    }

}
