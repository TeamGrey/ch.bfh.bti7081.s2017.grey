package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * @Author Quentin
 */
public interface PatientService {
    /**
     * Finds a patient by it's id
     *
     * @param id Id of the patient
     * @return Patient if found
     */
    Patient getPatientById(long id);
    /**
     * Creates a new patient
     *
     * @param firstname First name of the patient
     * @param lastname Last name of the patient
     */
    void createPatient(String firstname, String lastname);
    /**
     * Finds a patient by it's name
     *
     * @param firstName First name of the patient
     * @param lastName Last name of the patient
     * @return Patient if found
     */
    Patient getPatientByName(String firstName, String lastName);
    /**
     * Associates drugs with a patient
     *
     * @param patient Patient to be edited
     * @param drugs Drugs to be added
     */
    void addDrugsToPatient(Patient patient, List<Drug> drugs);
    /**
     * Associates habits with a patient
     *
     * @param patient Patient to be edited
     * @param habits Habits to be added
     */
    void addHabitsToPatient(Patient patient, List<Habit> habits);
    void removeDrugsFromPatient(Patient patient, List<Drug> drugs);
    void removeHabitsFromPatient(Patient patient, List<Habit> removed);
    /**
     * Returns all patients
     *
     * @return List of patients
     */
    List<Patient> getAllPatients();
}
