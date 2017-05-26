package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class DrugDao {

    private EntityManager entityManager;

    public DrugDao() {
        this.entityManager = EntityManagerSingleton.getInstance();
    }

    public Drug getDrugById(long id) {
        Drug drug =entityManager.find(Drug.class, id);
        return drug;
    }

    public List<Drug> getAllDrugs() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Drug> c = criteriaBuilder.createQuery(Drug.class);
        Root<Drug> drug = c.from(Drug.class);
        c.select(drug);

        TypedQuery<Drug> query = entityManager.createQuery(c);
        return query.getResultList();
    }

    public void createDrug(String name) {
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();
        Drug drug = new Drug();
        drug.setName(name);
        drug.setCreated(new Timestamp(instant.toEpochMilli()));
        drug.setChanged(new Timestamp(instant.toEpochMilli()));
        entityManager.persist(drug);
        entityManager.getTransaction().commit();
    }

    public void updateDrugName(long id, String newName) {
        Drug drug = entityManager.find(Drug.class, id);
        drug.setName(newName);
        Instant instant = Instant.now();
        drug.setChanged(new Timestamp(instant.toEpochMilli()));
        entityManager.getTransaction().commit();
    }

}
