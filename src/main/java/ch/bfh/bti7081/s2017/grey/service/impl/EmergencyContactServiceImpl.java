package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.EmergencyContactDao;
import ch.bfh.bti7081.s2017.grey.database.dao.impl.EmergencyContactDaoImpl;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.EmergencyContactService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private EmergencyContactDao dao;

    public EmergencyContactServiceImpl() {
        dao = new EmergencyContactDaoImpl();
    }

    @Override
    public EmergencyContact getEmergencyContactById(long id) {
        return dao.find(id);
    }

    @Override
    public void createEmerencyContact(String firstName, String lastName, String phoneNumber, Patient patient) {
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

    @Override
    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
        return dao.findEmergencyContactForPatient(patient);
    }





}
