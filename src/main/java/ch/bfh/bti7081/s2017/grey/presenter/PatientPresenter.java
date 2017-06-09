package ch.bfh.bti7081.s2017.grey.presenter;

import ch.bfh.bti7081.s2017.grey.view.PatientView;
import ch.bfh.bti7081.s2017.grey.view.PatientViewImpl;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.model.PatientModel;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

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

    }
    
    public PatientView getView(){
    	return patientView;
    }
}
