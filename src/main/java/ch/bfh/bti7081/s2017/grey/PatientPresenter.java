package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {
    PatientModel patientModel;
    PatientView patientView;
    PatientServiceImpl patientService = new PatientServiceImpl();
    public PatientPresenter(PatientView patientView, PatientModel patientModel){
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
    public void viewEntered(String user) {
    patientModel.setPatient(patientService.getPatientById(1));
    }
}
