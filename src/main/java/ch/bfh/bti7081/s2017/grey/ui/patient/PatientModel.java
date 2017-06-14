package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import javax.persistence.EntityManager;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {

  private EntityManager em = EntityManagerSingleton.getInstance();
  private PatientServiceImpl patientService = new PatientServiceImpl(em);
  private Patient patient = new Patient();

  public void setPatient(Patient newpatient) {
    this.patient = newpatient;
  }

  public void addPatient(Patient newpatient) {
    patientService.createPatient(newpatient.getFirstname(), newpatient.getLastname());
  }

  public Patient getPatient() {
    return patient;
  }

  public void editPatient(Patient changedpatient, int patientId) {
    this.patient = changedpatient;
    patientService.updatePatient(patient);
  }
}
