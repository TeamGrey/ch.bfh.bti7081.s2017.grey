package ch.bfh.bti7081.s2017.grey.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Quentin
 * @version 21.05.2017
 */
@Entity
@Table(name = "drug_task")
@IdClass(DrugTaskAssociationId.class)
public class DrugTaskAssociation {

  @Id
  @GeneratedValue
  private long id;
  @Id
  @Column(name = "drug_id")
  private long drugId;
  @Id
  @Column(name = "task_id")
  private long taskId;
  @Column(name = "amount")
  private int amount;
  @Column(name = "amount_units")
  private String amountUnits;
  @Column(name = "created")
  private Timestamp created;
  @Column(name = "changed")
  private Timestamp changed;
  @ManyToOne
  @JoinColumn(name = "drug_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Drug drug;
  @ManyToOne
  @JoinColumn(name = "task_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Task task;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDrugId() {
    return drugId;
  }

  public void setDrugId(long drugId) {
    this.drugId = drugId;
  }

  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getAmountUnits() {
    return amountUnits;
  }

  public void setAmountUnits(String amountUnits) {
    this.amountUnits = amountUnits;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Timestamp getChanged() {
    return changed;
  }

  public void setChanged(Timestamp changed) {
    this.changed = changed;
  }

  public Drug getDrug() {
    return drug;
  }

  public void setDrug(Drug drug) {
    this.drug = drug;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }
}
