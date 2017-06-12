package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.impl.EmergencyContactDao;
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
        dao = new EmergencyContactDao();
    }

    /**
     * Finds an emergency contact by it's id
     * @param id Id of the emergency contact
     * @return Emergency contact if found
     */
    @Override
    public EmergencyContact getEmergencyContactById(long id) {
        return dao.find(id);
    }

    /**
     * Creates a new emergency contact
     * @param firstName First name of the emergency contact
     * @param lastName Last name of the emergency contact
     * @param phoneNumber Phone number of the contact
     * @param patient Patient for which it is an emergency contact
     */
    @Override
    public void createEmergencyContact(String firstName, String lastName, String phoneNumber, Patient patient) {
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

    /**
     * Finds emergency contacts for the patient
     * @param patient Patient whose emergency contacts you want
     * @return List of emergency contacts
     */
    @Override
    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
        return dao.findEmergencyContactForPatient(patient);
    }





}
