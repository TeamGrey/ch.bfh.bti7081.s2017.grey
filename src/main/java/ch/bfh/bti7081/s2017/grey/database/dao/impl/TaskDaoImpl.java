package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.TaskDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {
    @Override
    public List<Task> getTasksByAppointment(Appointment appointment) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> task = criteriaQuery.from(Task.class);
        criteriaQuery.select(task).where(criteriaBuilder.equal(task.get("appointment"), appointment));

        TypedQuery<Task> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units) {
        em.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());
        for (Drug drug : drugs) {
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
        }
        em.getTransaction().commit();
    }
}
