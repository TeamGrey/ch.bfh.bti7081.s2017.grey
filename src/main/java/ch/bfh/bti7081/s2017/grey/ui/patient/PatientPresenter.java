package ch.bfh.bti7081.s2017.grey.ui.patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.EmergencyContactService;
import ch.bfh.bti7081.s2017.grey.service.HabitService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.HabitServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import javax.persistence.EntityManager;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {

  private PatientModel patientModel;
  private PatientView patientView;
  private EntityManager em = EntityManagerSingleton.getInstance();
  private PatientService patientService = new PatientServiceImpl(em);
  private HabitService habitService = new HabitServiceImpl(em);
  private EmergencyContactService emergencyContactService = new EmergencyContactServiceImpl(em);

  public PatientPresenter(PatientViewImpl patientView, PatientModel patientModel) {
    this.patientModel = patientModel;
    this.patientView = patientView;
    this.patientView.addListener(this);
  }

    @Override
    public void editClick() {
    }

    @Override
    public void saveClick() {

    }

    public void cancelClick() {

    }

    @Override
    public void setPatient(Patient patient) {
        this.patientModel.setPatient(patient);
        this.patientView.setPatient(this.patientModel.getPatient());
        this.setEmContact(emergencyContactService.findEmergencyContactForPatient(patient));
        this.setDrugList(patient.getDrugs());
        this.setHabitList(patient.getHabits());
    }

    public void setEmContact(List <EmergencyContact> emContact){
        this.patientModel.setEmContact(emContact);
        this.patientView.setEmContact(this.patientModel.getEmContact());
    }

    @Override
    public void setDrugList(List<PatientDrugAssociation> drugList) {
        this.patientModel.setDrugList(drugList);
        this.patientView.setDrugList(this.patientModel.getDrugList());
    }

    @Override
    public void setHabitList(List<PatientHabitAssociation> habitList) {

        this.patientModel.setHabitList(habitList);
        this.patientView.setHabitList(habitList);
    }

    @Override
    public void viewEntered(String user) {
    }

    public PatientView getView(){
    	return patientView;
    }
}
