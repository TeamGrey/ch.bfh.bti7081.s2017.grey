package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
public class Drug {
    @Id
    long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;
    @OneToMany(mappedBy = "drug")
    private List<PatientDrugAssociation> patients = new ArrayList<>();
    @OneToMany(mappedBy = "drug")
    private List<DrugTaskAssociation> tasks = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PatientDrugAssociation> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDrugAssociation> patients) {
        this.patients = patients;
    }

    public List<DrugTaskAssociation> getTasks() {
        return tasks;
    }

    public void setTasks(List<DrugTaskAssociation> tasks) {
        this.tasks = tasks;
    }
}
