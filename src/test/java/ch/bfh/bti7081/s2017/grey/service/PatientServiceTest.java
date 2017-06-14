package ch.bfh.bti7081.s2017.grey.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Quentin
 */
public class PatientServiceTest extends JPAHibernateTest {

  PatientService patientService;

  @Before
  public void setup() {
    patientService = new PatientServiceImpl(em);
  }

  @Test
  public void testCreatePatient() {
    patientService.createPatient("Unit", "Test");
    assertNotNull(patientService.getPatientByName("Unit", "Test"));
  }

  @Test
  public void getAllPatients() {
    List<Patient> patients = patientService.getAllPatients();
    int actualSize = patients.size() + 40;
    for (int i = 0; i < 40; i++) {
      patientService.createPatient("Unit" + i, "Test" + i);
    }
    patients = patientService.getAllPatients();
    assertEquals(actualSize, patients.size());
  }

}
