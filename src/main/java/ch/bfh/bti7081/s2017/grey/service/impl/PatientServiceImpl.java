package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.PatientDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class PatientServiceImpl implements PatientService {

  private PatientDao dao;

  public PatientServiceImpl(EntityManager em) {
    dao = new PatientDao(em);
  }

  /**
   * Finds a patient by it's id
   *
   * @param id Id of the patient
   * @return Patient if found
   */
  @Override
  public Patient getPatientById(long id) {
    return dao.find(id);
  }

  /**
   * Creates a new patient
   *
   * @param firstname First name of the patient
   * @param lastname Last name of the patient
   */
  @Override
  public void createPatient(String firstname, String lastname) {
    Instant instant = Instant.now();

    Patient patient = new Patient();
    patient.setFirstname(firstname);
    patient.setLastname(lastname);
    patient.setCreated(new Timestamp(instant.toEpochMilli()));
    patient.setChanged(new Timestamp(instant.toEpochMilli()));

    dao.create(patient);


  }

  /**
   * Updates a patient
   *
   * @param patient Patient to be updated
   */
  public void updatePatient(Patient patient) {
    dao.update(patient);
  }

  /**
   * Finds a patient by it's name
   *
   * @param firstName First name of the patient
   * @param lastName Last name of the patient
   * @return Patient if found
   */
  @Override
  public Patient getPatientByName(String firstName, String lastName) {
    return dao.getPatientByName(firstName, lastName);
  }

  /**
   * Associates drugs with a patient
   *
   * @param patient Patient to be edited
   * @param drugs Drugs to be added
   */
  @Override
  public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
    dao.addDrugsToPatient(patient, drugs);
  }

  /**
   * Associates habits with a patient
   *
   * @param patient Patient to be edited
   * @param habits Habits to be added
   */
  @Override
  public void addHabitsToPatient(Patient patient, List<Habit> habits) {
    dao.addHabitsToPatient(patient, habits);
  }

  /**
   * Returns all patients
   *
   * @return List of patients
   */
  @Override
  public List<Patient> getAllPatients() {
    return dao.findAll();
  }

}

