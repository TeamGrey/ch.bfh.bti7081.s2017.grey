package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientViewListener {

    private PatientModel patientModel;
    private PatientView patientView;

    public PatientPresenter(PatientViewImpl patientView, PatientModel patientModel) {
        this.patientModel = patientModel;
        this.patientView = patientView;
        setPatient(patientModel.getPatient());
        this.patientView.addListener(this);
    }

    public PatientView getView() {
        return patientView;
    }

    public void setPatient(Patient patient) {
        patientModel.setPatient(patient);
        patientView.setPatient(patient);
        patientView.setDrugOptions(patientModel.getAllDrugs());
        patientView.setHabitOptions(patientModel.getAllHabits());
        patientView.setDrugList(patientModel.getDrugList());
        patientView.setEmContact(patientModel.getEmContact());
        patientView.setHabitList(patientModel.getHabitList());
    }

    @Override
    public void viewEntered(String view) {

    }

    @Override
    public void onDrugSelectionChange(List<Drug> added, List<Drug> removed, List<Drug> selected) {
        patientModel.updateDrugs(added, removed);
        patientView.setDrugList(selected);
    }

    @Override
    public void onEMCAddition(String firstName, String lastName, String phone, long emdId) {
        if(emdId <= 0) {
            patientModel.addEemergenyContact(firstName, lastName, phone);
        } else {
            patientModel.updateEmergencyContact(firstName, lastName, phone, emdId);
        }
        patientView.setEmContact(patientModel.getEmContact());
    }

    @Override
    public void onHabitSelectionChange(List<Habit> added, List<Habit> removed, List<Habit> selected) {
        patientModel.updateHabits(added, removed);
        patientView.setHabitList(selected);
    }
}
