package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientDrugAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientHabitAssociation;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by gabor on 29/05/17.
 */
public class PatientDao extends GenericDaoImpl<Patient> implements GenericDao<Patient> {

  public PatientDao(EntityManager em) {
    super(em);
  }

  /**
   * Find a patient by name
   *
   * @param firstName First name of patient
   * @param lastName Last name of patient
   * @return Patient
   */
  public Patient getPatientByName(String firstName, String lastName) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
    Root<Patient> patient = criteriaQuery.from(Patient.class);
    criteriaQuery.select(patient).where(criteriaBuilder.equal(patient.get("firstname"), firstName),
        criteriaBuilder.equal(patient.get("lastname"), lastName));

    TypedQuery<Patient> query = em.createQuery(criteriaQuery);
    return query.getSingleResult();
  }

  /**
   * Associate a list of drugs with a patient
   *
   * @param patient Patient to be edited
   * @param drugs Drugs to be added
   */
  public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
    em.getTransaction().begin();
    for (Drug drug : drugs) {
      PatientDrugAssociation association = new PatientDrugAssociation();
      association.setPatient(patient);
      association.setDrug(drug);
      association.setPatientId(patient.getId());
      association.setDrugId(drug.getId());
      Instant instant = Instant.now();
      association.setCreated(new Timestamp(instant.toEpochMilli()));
      association.setChanged(new Timestamp(instant.toEpochMilli()));
      em.persist(association);
      patient.getDrugs().add(association);
      drug.getPatients().add(association);
    }
    em.getTransaction().commit();
  }

  /**
   * Associate a list of habits with a patient
   *
   * @param patient Patient to be edited
   * @param habits Habits to be added
   */
  public void addHabitsToPatient(Patient patient, List<Habit> habits) {
    em.getTransaction().begin();
    for (Habit habit : habits) {
      PatientHabitAssociation association = new PatientHabitAssociation();
      association.setPatient(patient);
      association.setHabit(habit);
      association.setPatientId(patient.getId());
      association.setHabitId(habit.getId());
      Instant instant = Instant.now();
      association.setCreated(new Timestamp(instant.toEpochMilli()));
      association.setChanged(new Timestamp(instant.toEpochMilli()));
      em.persist(association);
      patient.getHabits().add(association);
      habit.getPatients().add(association);
    }
    em.getTransaction().commit();
  }
}
