package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.EmergencyContactDao;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.service.EmergencyContactService;

import java.util.List;

/**
 * @Author Quentin
 */
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private EmergencyContactDao emergencyContactDao;

    public EmergencyContactServiceImpl() {
        emergencyContactDao = new EmergencyContactDao();
    }

    @Override
    public EmergencyContact getEmergencyContactById(long id) {
        return emergencyContactDao.getEmergencyContactById(id);
    }

    @Override
    public void createEmerencyContact(String firstName, String lastName, String phoneNumber, Patient patient) {
        emergencyContactDao.createEmergencyContact(firstName, lastName, phoneNumber, patient);
    }

    @Override
    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
        return emergencyContactDao.findEmergencyContactForPatient(patient);
    }





}
