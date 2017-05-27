package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
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
public class TaskDao {

    private EntityManager entityManager;

    public TaskDao () {
        entityManager = EntityManagerSingleton.getInstance();
    }

    public Task findTaskById(long id) {
        return entityManager.find(Task.class, id);
    }

    public List<Task> getAllTasks() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> task = criteriaQuery.from(Task.class);
        criteriaQuery.select(task);

        TypedQuery<Task> query = entityManager.createQuery(criteriaQuery);
        List<Task> tasks = query.getResultList();
        return tasks;
    }

    public void createTask(String name, Appointment appointment) {
        entityManager.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        Task task = new Task();
        task.setName(name);
        task.setAppointment(appointment);
        task.setCreated(timestamp);
        task.setChanged(timestamp);

        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }

    public void addDrugsToTask(Task task, List<Drug> drugs, int amount, String units) {
        entityManager.getTransaction().begin();
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
            entityManager.persist(association);
            task.getDrugs().add(association);
            drug.getTasks().add(association);
        }
        entityManager.getTransaction().commit();
    }
    
    public List<Task> getTasksByAppointment(Appointment appointment){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> task = criteriaQuery.from(Task.class);
        criteriaQuery.select(task).where(criteriaBuilder.equal(task.get("appointment"), appointment));

        TypedQuery<Task> query = entityManager.createQuery(criteriaQuery);
        List<Task> tasks = query.getResultList();
        return tasks;
    }
    public void setDuration(Task task, int amount){
        entityManager.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

    	task.setDuration(amount);
    	task.setChanged(timestamp);

    	entityManager.persist(task);
    	entityManager.getTransaction().commit();
    }

    public void toggleActiveStatus(Task task, Boolean status){
        entityManager.getTransaction().begin();
        Instant instant = Instant.now();
        Timestamp timestamp = new Timestamp(instant.toEpochMilli());

        task.toggleFinished(status);
        task.setChanged(timestamp);

        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }
}
