package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Quentin
 */
public class PatientDaoTest {

    @Test
    @Ignore
    public void testCreatePatient(){
        PatientDao patientDao = new PatientDao();
        DrugDao drugDao = new DrugDao();
;
        List<Drug> drugs = new ArrayList<>();
        Drug drug = drugDao.getDrugById(0);
        drugs.add(drug);
        Patient patient = patientDao.getPatientByName("Test", "Test");
        patientDao.addDrugsToPatient(patient, drugs);
        patient = patientDao.getPatientByName("Test", "Test");
    }
}
