package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.PatientDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.PatientService;

import java.util.List;

/**
 * @Author Quentin
 */
public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;

    public PatientServiceImpl() {
        patientDao = new PatientDao();
    }
    @Override
    public Patient getPatientById(long id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public void createStaff(String firstname, String lastname) {
        patientDao.createPatient(firstname, lastname);
    }

    @Override
    public Patient getPatientByName(String firstName, String lastName) {
        return patientDao.getPatientByName(firstName, lastName);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.getPatients();
    }
}
