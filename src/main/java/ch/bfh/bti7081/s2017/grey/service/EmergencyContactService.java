package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * @Author Quentin
 */
public interface EmergencyContactService {
    /**
     * Finds an emergency contact by it's id
     *
     * @param id Id of the emergency contact
     * @return Emergency contact if found
     */
    EmergencyContact getEmergencyContactById(long id);
    /**
     * Creates a new emergency contact
     *
     * @param firstName First name of the emergency contact
     * @param lastName Last name of the emergency contact
     * @param phoneNumber Phone number of the contact
     * @param patient Patient for which it is an emergency contact
     */
    void createEmergencyContact(String firstName, String lastName, String phoneNumber, Patient patient);
    /**
     * Finds emergency contacts for the patient
     *
     * @param patient Patient whose emergency contacts you want
     * @return List of emergency contacts
     */
    List<EmergencyContact> findEmergencyContactForPatient(Patient patient);
}
