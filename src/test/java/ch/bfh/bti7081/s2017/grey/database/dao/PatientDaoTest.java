package ch.bfh.bti7081.s2017.grey.database.dao;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @Author Quentin
 */
public class PatientDaoTest {

    @Test
    @Ignore
    public void testCreatePatient(){
        PatientDao patientDao = new PatientDao();

        patientDao.createPatient("Test", "Test");
    }
}
