package ch.bfh.bti7081.s2017.grey.database;

import ch.bfh.bti7081.s2017.grey.database.dao.PatientDao;
import ch.bfh.bti7081.s2017.grey.database.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author Joel
 */
public class DrugsOfPatientTest {
    private Patient patient;
    private PatientDao patDao;

    @Before
    public void setup() {
        patDao = new PatientDao();
        patient = patDao.getPatientById(4);
    }

    @Test
    public void listDrugs() {
        List<PatientDrugAssociation> drugs = patient.getDrugs();

        for(PatientDrugAssociation drugAsoc: drugs) {
                System.out.println(drugAsoc.getDrug().getName());
        }

        assertEquals(2, drugs.size());
    }
}
