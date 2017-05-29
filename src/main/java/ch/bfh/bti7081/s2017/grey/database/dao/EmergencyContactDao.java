package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface EmergencyContactDao extends GenericDao<EmergencyContact> {
    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient);
}
