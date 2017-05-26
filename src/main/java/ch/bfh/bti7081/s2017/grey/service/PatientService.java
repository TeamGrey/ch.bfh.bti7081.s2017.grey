package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * @Author Quentin
 */
public interface PatientService {
    Patient getPatientById(long id);

    void createPatient(String firstname, String lastname);

    Patient getPatientByName(String firstName, String lastName);
    List<Patient> getAllPatients();
}
