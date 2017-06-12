package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import com.vaadin.server.VaadinSession;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {
    PatientModel patientModel;
    PatientView patientView;
    PatientServiceImpl patientService = new PatientServiceImpl();
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
    }

    public void setEmContact(List <EmergencyContact> emContact){
        this.patientModel.setEmContact(emContact);
        System.out.println(patientModel.getEmContact());
        this.patientView.setEmContact(this.patientModel.getEmContact());
    }

    @Override
    public void viewEntered(String user) {
    }

    public PatientView getView(){
    	return patientView;
    }
}
