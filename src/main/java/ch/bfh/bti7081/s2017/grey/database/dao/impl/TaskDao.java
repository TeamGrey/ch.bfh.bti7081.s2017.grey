package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by gabor on 29/05/17.
 */
public class TaskDao extends GenericDaoImpl<Task> implements GenericDao<Task> {

  public TaskDao(EntityManager em) {
    super(em);
  }

  /**
   * Find all tasks for a specific appointment
   *
   * @param appointment Appointment whose tasks you want
   * @return List of tasks
   */
  public List<Task> getTasksByAppointment(Appointment appointment) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
    Root<Task> task = criteriaQuery.from(Task.class);
    criteriaQuery.select(task).where(criteriaBuilder.equal(task.get("appointment"), appointment))
        .orderBy(criteriaBuilder.asc(task.get("id")));

    TypedQuery<Task> query = em.createQuery(criteriaQuery);
    return query.getResultList();
  }

  /**
   * Associate a drug with a task
   *
   * @param task Task to be edited
   * @param drug Drug to be added
   * @param amount Amount of the drug
   * @param units Unit of the amount
   */
  public void addDrugToTask(Task task, Drug drug, int amount, String units) {
    em.getTransaction().begin();
    Instant instant = Instant.now();
    Timestamp timestamp = new Timestamp(instant.toEpochMilli());
    DrugTaskAssociation association = new DrugTaskAssociation();
    association.setTask(task);
    association.setDrug(drug);
    association.setTaskId(task.getId());
    association.setDrugId(drug.getId());
    association.setAmount(amount);
    association.setAmountUnits(units);
    association.setCreated(timestamp);
    association.setChanged(timestamp);
    em.persist(association);
    task.getDrugs().add(association);
    drug.getTasks().add(association);
    em.getTransaction().commit();
  }
}
