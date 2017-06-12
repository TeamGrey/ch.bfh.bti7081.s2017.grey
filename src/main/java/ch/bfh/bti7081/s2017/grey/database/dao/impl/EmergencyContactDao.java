package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by gabor on 29/05/17.
 */
public class EmergencyContactDao extends GenericDaoImpl<EmergencyContact> implements
    GenericDao<EmergencyContact> {

  public EmergencyContactDao(EntityManager em) {
    super(em);
  }

  public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<EmergencyContact> criteriaQuery = criteriaBuilder
        .createQuery(EmergencyContact.class);
    Root<EmergencyContact> contactRoot = criteriaQuery.from(EmergencyContact.class);
    criteriaQuery.select(contactRoot)
        .where(criteriaBuilder.equal(contactRoot.get("patient"), patient));

    TypedQuery<EmergencyContact> query = em.createQuery(criteriaQuery);
    return query.getResultList();
  }
}
