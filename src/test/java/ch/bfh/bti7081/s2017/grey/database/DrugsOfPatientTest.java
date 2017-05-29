package ch.bfh.bti7081.s2017.grey.database;

import ch.bfh.bti7081.s2017.grey.database.entity.*;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author Joel
 */
public class DrugsOfPatientTest {
    private Patient patient;
    private PatientService patientService;

    @Before
    public void setup() {
        patientService = new PatientServiceImpl();
        patient = patientService.getPatientById(4);
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
