package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @Author Quentin
 */
public class EmergencyContactDao {
    private EntityManager entityManager;

    public EmergencyContactDao() {
        this.entityManager = EntityManagerSingleton.getInstance();
    }

    public EmergencyContact getEmergencyContactById(long id) {
        EmergencyContact emergencyContact = entityManager.find(EmergencyContact.class, id);
        return emergencyContact;
    }

    public List<EmergencyContact> findEmergencyContactForPatient(Patient patient) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmergencyContact> criteriaQuery = criteriaBuilder.createQuery(EmergencyContact.class);
        Root<EmergencyContact> contactRoot =criteriaQuery.from(EmergencyContact.class);
        criteriaQuery.select(contactRoot).where(criteriaBuilder.equal(contactRoot.get("patient"), patient));

        TypedQuery<EmergencyContact> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void createEmergencyContact(String firstName, String lastName, String phoneNumber, Patient patient) {
        entityManager.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setFirstname(firstName);
        emergencyContact.setLastname(lastName);
        emergencyContact.setPhonenumber(phoneNumber);
        emergencyContact.setPatient(patient);
        emergencyContact.setCreated(timestamp);
        emergencyContact.setChanged(timestamp);

        entityManager.persist(emergencyContact);
        entityManager.getTransaction().commit();
    }
}
