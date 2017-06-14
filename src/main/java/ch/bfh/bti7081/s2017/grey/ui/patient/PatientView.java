package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.*;

import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public interface PatientView {
    void setPatient(Patient randpat);

    void setEmContact(List<EmergencyContact> emContact);

    void setDrugList(List<PatientDrugAssociation> drugList);

    void setHabitList(List<PatientHabitAssociation> habitList);

    interface PatientViewListener {
        void editClick();

        void saveClick();

        void cancelClick();

        void setEmContact(List<EmergencyContact> emcontact);

        void setDrugList(List<PatientDrugAssociation> drugList);

        void setHabitList(List<PatientHabitAssociation> habitList);

        void setPatient(Patient patient);

        void viewEntered(String user);
    }

    public void addListener(PatientViewListener listener);
}
