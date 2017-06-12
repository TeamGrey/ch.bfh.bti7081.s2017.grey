package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public interface PatientView {
    void setPatient(Patient randpat);


    void setEmContact(List<EmergencyContact> emContact);

    interface PatientViewListener {
        void editClick();

        void saveClick();

        void cancelClick();

        void setEmContact(List<EmergencyContact> emcontact);

        void setPatient(Patient patient);

        void viewEntered(String user);
    }

    public void addListener(PatientViewListener listener);
}
