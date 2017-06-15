package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
     * @param lastName  Last name of patient
     * @return Patient
     */
    public Patient getPatientByName(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> patient = criteriaQuery.from(Patient.class);
        criteriaQuery.select(patient).where(criteriaBuilder.equal(patient.get("firstname"), firstName),
                criteriaBuilder.equal(patient.get("lastname"), lastName));

        TypedQuery<Patient> query = em.createQuery(criteriaQuery);
        Patient result = query.getSingleResult();
        return result;
    }

    /**
     * check whether a association between patient and drug exists
     *
     * @param patient Patient to be checked
     * @param drug    Drug to be checked
     * @return
     */
    public boolean hasDrug(Patient patient, Drug drug) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PatientDrugAssociation> criteriaQuery = criteriaBuilder.createQuery(PatientDrugAssociation.class);
        Root<PatientDrugAssociation> patientDrugAssociationRoot = criteriaQuery.from(PatientDrugAssociation.class);
        criteriaQuery.select(patientDrugAssociationRoot).where(criteriaBuilder.equal(patientDrugAssociationRoot.get("patientId"), patient.getId()),
                criteriaBuilder.equal(patientDrugAssociationRoot.get("drugId"), drug.getId()));

        TypedQuery<PatientDrugAssociation> query = em.createQuery(criteriaQuery);
        List results = query.getResultList();
        return results.size() >= 1;
    }

    /**
     * Associate a list of drugs with a patient
     *
     * @param patient Patient to be edited
     * @param drugs   Drugs to be added
     */
    public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
        em.getTransaction().begin();
        for (Drug drug : drugs) {
            if (this.hasDrug(patient, drug))
                continue; // skip existing association
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
     * remove association between patient and drug
     *
     * @param patient
     * @param drug
     */
    public void removeDrugFromPatient(Patient patient, Drug drug) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<PatientDrugAssociation> delete = criteriaBuilder.createCriteriaDelete(PatientDrugAssociation.class);
        Root<PatientDrugAssociation> e = delete.from(PatientDrugAssociation.class);
        delete.where(criteriaBuilder.equal(e.get("patientId"), patient.getId()), criteriaBuilder.equal(e.get("drugId"), drug.getId()));

        PatientDrugAssociation patientDrugAssociation = null;
        for (PatientDrugAssociation assoc : patient.getDrugs()) {
            if (assoc.getDrug() == drug) {
                patientDrugAssociation = assoc;
                break;
            }
        }

        if (patientDrugAssociation != null) {
            em.getTransaction().begin();
            patient.getDrugs().remove(patientDrugAssociation);
            drug.getPatients().remove(patientDrugAssociation);
            em.createQuery(delete).executeUpdate();
            em.getTransaction().commit();
        }
    }


    /**
     * remove association between patient and habit
     *
     * @param patient
     * @param habit
     */
    public void removeHabitFromPatient(Patient patient, Habit habit) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<PatientHabitAssociation> delete = criteriaBuilder.createCriteriaDelete(PatientHabitAssociation.class);
        Root<PatientHabitAssociation> e = delete.from(PatientHabitAssociation.class);
        delete.where(criteriaBuilder.equal(e.get("patientId"), patient.getId()), criteriaBuilder.equal(e.get("habitId"), habit.getId()));

        PatientHabitAssociation patientHabitAssociation = null;
        for (PatientHabitAssociation assoc : patient.getHabits()) {
            if (assoc.getHabit() == habit) {
                patientHabitAssociation = assoc;
                break;
            }
        }

        if (patientHabitAssociation != null) {
            em.getTransaction().begin();
            patient.getHabits().remove(patientHabitAssociation);
            habit.getPatients().remove(patientHabitAssociation);
            em.createQuery(delete).executeUpdate();
            em.getTransaction().commit();
        }
    }

    /**
     * Associate a list of habits with a patient
     *
     * @param patient Patient to be edited
     * @param habits  Habits to be added
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
