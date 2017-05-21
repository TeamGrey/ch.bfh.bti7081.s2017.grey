package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Quentin
 * @version 21.05.2017
 */
@Entity
@Table("drug_task")
@IdClass(DrugTaskAssociationId.class)
public class DrugTaskAssociation {
    @Id
    private long drugId;
    @Id
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
    @PrimaryKeyJoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drug drug;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

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
