package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class DrugDao {

    public Drug getDrugById(long id) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        Drug drug =entityManager.find(Drug.class, id);
        return drug;
    }

    public void createDrug(String name) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();
        Drug drug = new Drug();
        drug.setName(name);
        drug.setCreated(new Timestamp(instant.toEpochMilli()));
        drug.setChanged(new Timestamp(instant.toEpochMilli()));
        entitymanager.persist(drug);
        entitymanager.getTransaction().commit();
    }

}
