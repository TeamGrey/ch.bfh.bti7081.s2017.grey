package ch.bfh.bti7081.s2017.grey.view;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

/**
 * Created by hannes on 5/17/17.
 */
public interface PatientView {
    void setPatient(Patient randpat);

    interface PatientViewListener {
        void editClick();

        void saveClick();

        void cancelClick();

        void setPatient(Patient patient);
    }

    public void addListener(PatientViewListener listener);
}