package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import com.vaadin.server.VaadinSession;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {
    PatientModel patientModel;
    PatientView patientView;
    PatientServiceImpl patientService = new PatientServiceImpl();
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
    }

    @Override
    public void viewEntered(String user) {
    }

    public PatientView getView(){
    	return patientView;
    }
}
