package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.EmergencyContactDao;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.EmergencyContactService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @Author Quentin
 */
public class EmergencyContactServiceImpl implements EmergencyContactService {

  private EmergencyContactDao dao;

  public EmergencyContactServiceImpl(EntityManager em) {
    dao = new EmergencyContactDao(em);
  }

  /** @see EmergencyContactService#getEmergencyContactById(long) */
  @Override
  public EmergencyContact getEmergencyContactById(long id) {
    return dao.find(id);
  }

  /** @see EmergencyContactService#createEmergencyContact(String, String, String, Patient) */
  @Override
  public void createEmergencyContact(String firstName, String lastName, String phoneNumber,
      Patient patient) {
    Instant instant = Instant.now();
    Timestamp timestamp = new Timestamp(instant.toEpochMilli());

    EmergencyContact emergencyContact = new EmergencyContact();
    emergencyContact.setFirstname(firstName);
    emergencyContact.setLastname(lastName);
    emergencyContact.setPhonenumber(phoneNumber);
    emergencyContact.setPatient(patient);
    emergencyContact.setCreated(timestamp);
    emergencyContact.setChanged(timestamp);

    dao.create(emergencyContact);
  }

  /** @see EmergencyContactService#findEmergencyContactForPatient(Patient) */
  @Override
  public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
    return dao.findEmergencyContactForPatient(patient);
  }

}
