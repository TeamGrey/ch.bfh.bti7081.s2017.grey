package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.HabitServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {
    PatientModel patientModel;
    PatientView patientView;
    PatientServiceImpl patientService = new PatientServiceImpl();
    HabitServiceImpl habitService = new HabitServiceImpl();
    EmergencyContactServiceImpl emergencyContactService = new EmergencyContactServiceImpl();
    public PatientPresenter(PatientViewImpl patientView, PatientModel patientModel){
        this.patientModel=patientModel;
        this.patientView=patientView;
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
        this.setEmContact(this.emergencyContactService.findEmergencyContactForPatient(patient));
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
