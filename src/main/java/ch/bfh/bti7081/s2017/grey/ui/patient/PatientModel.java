package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.HabitServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {
   List emContact = new ArrayList <>();
   List drugList = new ArrayList<>();
   List habitList = new ArrayList<>();
   private EntityManager em = EntityManagerSingleton.getInstance();
   HabitServiceImpl habitService = new HabitServiceImpl(em);


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
    this.patient = patient;
    patientService.updatePatient(patient);
  }

  public List<PatientDrugAssociation> getDrugList(){
      return this.drugList;
  }
  public void setDrugList(List <PatientDrugAssociation> drugList){
      this.drugList=drugList;
  }
  public List <PatientHabitAssociation> getHabitList(){return this.habitList;}

  public void setHabitList (List<PatientHabitAssociation> habitList){
      this.habitList=habitList;
  }
  public List<EmergencyContact> getEmContact(){
      return this.emContact;
  }

    public void setEmContact(List <EmergencyContact> emContact) {
        this.emContact = emContact;
    }
}
