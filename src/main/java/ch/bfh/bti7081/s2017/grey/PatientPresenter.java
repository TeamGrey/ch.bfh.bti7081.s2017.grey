package ch.bfh.bti7081.s2017.grey;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {
    PatientModel patientModel;
    PatientView patientView;
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
}
