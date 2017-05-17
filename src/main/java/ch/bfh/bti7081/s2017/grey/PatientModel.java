package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import com.vaadin.data.Binder;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {
    private Binder<Patient> binder = new Binder<Patient>(Patient.class);
    private Patient patient = new Patient();
    public void setView(Object view){
        binder.bindInstanceFields(view);
    }
    public void setPatient(Patient patient){
        this.patient = patient;
        binder.setBean(patient);
    }
}
