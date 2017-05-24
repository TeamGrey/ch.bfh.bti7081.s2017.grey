package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.*;
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
public class PatientDao {

    EntityManager entityManager;

    public PatientDao() {
        entityManager = EntityManagerSingleton.getInstance();
    }

    public Patient getPatientById(long id) {
        Patient patient = entityManager.find( Patient.class, id );
        return patient;
    }

    public Patient getPatientByName(String firstName, String lastName) {
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
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();

        Patient patient = new Patient();
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setChanged(new Timestamp(instant.toEpochMilli()));
        patient.setCreated(new Timestamp(instant.toEpochMilli()));

        entityManager.persist(patient);
        entityManager.getTransaction().commit();
    }

    public void addDrugsToPatient(Patient patient, List<Drug> drugs){
        entityManager.getTransaction().begin();
        for (Drug drug : drugs) {
            PatientDrugAssociation association = new PatientDrugAssociation();
            association.setPatient(patient);
            association.setDrug(drug);
            association.setPatientId(patient.getId());
            association.setDrugId(drug.getId());
            Instant instant = Instant.now();
            association.setCreated(new Timestamp(instant.toEpochMilli()));
            association.setChanged(new Timestamp(instant.toEpochMilli()));
            entityManager.persist(association);
            patient.getDrugs().add(association);
            drug.getPatients().add(association);
        }
        entityManager.getTransaction().commit();
    }

    public void addHabitsToPatient(Patient patient, List<Habit> habits) {
        entityManager.getTransaction().begin();
        for (Habit habit : habits) {
            PatientHabitAssociation association = new PatientHabitAssociation();
            association.setPatient(patient);
            association.setHabit(habit);
            association.setPatientId(patient.getId());
            association.setHabitId(habit.getId());
            Instant instant = Instant.now();
            association.setCreated(new Timestamp(instant.toEpochMilli()));
            association.setChanged(new Timestamp(instant.toEpochMilli()));
            entityManager.persist(association);
            patient.getHabits().add(association);
            habit.getPatients().add(association);
        }
        entityManager.getTransaction().commit();
    }
}
