package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.DrugTaskAssociation;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;

import javax.persistence.EntityManager;
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
    
//    public void getTasksByAppointment(Appointment appointment){
//    	// SELECT t.* FROM tasks AS t
//    	// INNER JOIN appointment_task at ON at.task_id = t.id
//    	// INNER JOIN appointment a ON at.appointment_id = a.id
//    	// WHERE a.id = XY
//    }
//    public void setDuration(Task task, int amount){
//    	task.setDuration(amount);
//    }
//    public void addToDuration(Task task, int amount){
//    	int newAmount = task.getDuration() + amount;
//    	task.setDuration(newAmount);
//    }
//    public void removeFromDuration(Task task, int amount){
//    	int newAmount = task.getDuration() - amount;
//    	task.setDuration(newAmount);
//    }
//    public void setActiveStatus(Task task, boolean status){
//    	task.setFinished(status);
//    }

}
