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

  /** @see PatientService#getPatientById(long) */
  @Override
  public Patient getPatientById(long id) {
    return dao.find(id);
  }

  /** @see PatientService#createPatient(String, String) */
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

  /** @see PatientService#getPatientByName(String, String) */
  @Override
  public Patient getPatientByName(String firstName, String lastName) {
    return dao.getPatientByName(firstName, lastName);
  }

  /** @see PatientService#addDrugsToPatient(Patient, List<Drug>) */
  @Override
  public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
    dao.addDrugsToPatient(patient, drugs);
  }

  /** @see PatientService#addHabitsToPatient(Patient, List<Habit>) */
  @Override
  public void addHabitsToPatient(Patient patient, List<Habit> habits) {
    dao.addHabitsToPatient(patient, habits);
  }

  /** @see PatientService#getAllPatients() */
  @Override
  public List<Patient> getAllPatients() {
    return dao.findAll();
  }

}

