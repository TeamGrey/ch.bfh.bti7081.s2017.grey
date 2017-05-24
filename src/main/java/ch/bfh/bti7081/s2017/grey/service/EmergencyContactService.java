package ch.bfh.bti7081.s2017.grey.service;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * @Author Quentin
 */
public interface EmergencyContactService {
    EmergencyContact getEmergencyContactById(long id);

    void createEmerencyContact(String firstName, String lastName, String phoneNumber, Patient patient);

    List<EmergencyContact> findEmergencyContactForPatient(Patient patient);
}
