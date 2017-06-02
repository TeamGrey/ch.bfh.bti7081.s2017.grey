package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {
   PatientServiceImpl patientService = new PatientServiceImpl();
   Patient patient = new Patient();

   public void setPatient(Patient newpatient) {
      this.patient = newpatient;
   }

   public void addPatient(Patient newpatient) {
      patientService.createPatient(newpatient.getFirstname(), newpatient.getLastname());
   }
  public Patient getPatient(){
  return patient;
  }
  public void editPatient(Patient changedpatient, int patientId){
      this.patient = patient;
      patientService.updatePatient(patient);
  }
}
