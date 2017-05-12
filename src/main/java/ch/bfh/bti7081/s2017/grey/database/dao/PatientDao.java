package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import javax.persistence.EntityManager;
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

    public Patient getPatientById(int id) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        Patient patient = entitymanager.find( Patient.class, id );
        return patient;
    }

    public Patient getPatientByName(String firstName, String lastName) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> patient = criteriaQuery.from(Patient.class);
        criteriaQuery.select(patient).where(criteriaBuilder.equal(patient.get("firstname"), firstName),
                criteriaBuilder.equal(patient.get("lastname"), lastName));

        TypedQuery<Patient> query = entityManager.createQuery(criteriaQuery);
        Patient result = query.getSingleResult();
        return result;
    }

    public void createPatient(String firstname, String lastname) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();

        Patient patient = new Patient();
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setChanged(new Timestamp(instant.toEpochMilli()));
        patient.setCreated(new Timestamp(instant.toEpochMilli()));

        entitymanager.persist(patient);
        entitymanager.getTransaction().commit();
    }
}
