package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.PatientDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Habit;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.PatientService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class PatientServiceImpl implements PatientService {

    private PatientDao dao;

    public PatientServiceImpl() {
        dao = new PatientDao();
    }

    @Override
    public Patient getPatientById(long id) {
        return dao.find(id);
    }

    @Override
    public void createPatient(String firstname, String lastname) {
        Instant instant = Instant.now();

        Patient patient = new Patient();
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setCreated(new Timestamp(instant.toEpochMilli()));
        patient.setChanged(new Timestamp(instant.toEpochMilli()));

        dao.create(patient);


    }
    public void updatePatient(Patient patient){
        dao.update(patient);
    }

    @Override
    public Patient getPatientByName(String firstName, String lastName) {
        return dao.getPatientByName(firstName, lastName);
    }

    @Override
    public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
        dao.addDrugsToPatient(patient, drugs);
    }

    @Override
    public void addHabitsToPatient(Patient patient, List<Habit> habits) {
        dao.addHabitsToPatient(patient, habits);
    }

    @Override
    public List<Patient> getAllPatients() {
        return dao.findAll();
    }

}

