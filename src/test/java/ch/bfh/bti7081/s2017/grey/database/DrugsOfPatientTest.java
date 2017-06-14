package ch.bfh.bti7081.s2017.grey.database;

import static org.junit.Assert.assertEquals;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientDrugAssociation;
import ch.bfh.bti7081.s2017.grey.database.util.JPAHibernateTest;
import ch.bfh.bti7081.s2017.grey.service.DrugService;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.impl.DrugServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Joel
 */
public class DrugsOfPatientTest extends JPAHibernateTest{

  private Patient patient;
  private PatientService patientService;
  private DrugService drugService;

  @Before
  public void setup() {
    patientService = new PatientServiceImpl(em);
    drugService = new DrugServiceImpl(em);
    patient = patientService.getPatientById(4);
  }

  @Test
  public void listDrugs() {
    List<Drug> drugs = new ArrayList<>();
    drugs.add(drugService.getDrugById(0));
    drugs.add(drugService.getDrugById(1));
    patientService.addDrugsToPatient(patient, drugs);

    assertEquals(2, drugs.size());
  }
}
