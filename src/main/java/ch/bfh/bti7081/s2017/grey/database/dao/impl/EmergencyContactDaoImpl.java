package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.EmergencyContactDao;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public class EmergencyContactDaoImpl extends GenericDaoImpl<EmergencyContact> implements EmergencyContactDao {
    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<EmergencyContact> criteriaQuery = criteriaBuilder.createQuery(EmergencyContact.class);
        Root<EmergencyContact> contactRoot =criteriaQuery.from(EmergencyContact.class);
        criteriaQuery.select(contactRoot).where(criteriaBuilder.equal(contactRoot.get("patient"), patient));

        TypedQuery<EmergencyContact> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
