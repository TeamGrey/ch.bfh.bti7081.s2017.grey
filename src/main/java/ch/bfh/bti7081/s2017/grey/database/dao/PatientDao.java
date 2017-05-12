package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @Author Quentin
 */
public class PatientDao {

    public Patient getPatientById(long id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CRM" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Patient patient = entitymanager.find( Patient.class, id );
        entitymanager.close();
        emfactory.close();
        return patient;
    }

    public Patient getPatientByName(String firstName, String lastName) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CRM");
        EntityManager entityManager = emfactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> patient = criteriaQuery.from(Patient.class);
        criteriaQuery.select(patient).where(criteriaBuilder.equal(patient.get("firstname"), firstName),
                criteriaBuilder.equal(patient.get("lastname"), lastName));

        TypedQuery<Patient> query = entityManager.createQuery(criteriaQuery);
        Patient result = query.getSingleResult();
        entityManager.close();
        emfactory.close();
        return result;
    }

    public void createPatient(String firstname, String lastname) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CRM");

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();

        Patient patient = new Patient();
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setChanged(new Timestamp(instant.toEpochMilli()));
        patient.setCreated(new Timestamp(instant.toEpochMilli()));

        entitymanager.persist(patient);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
}
