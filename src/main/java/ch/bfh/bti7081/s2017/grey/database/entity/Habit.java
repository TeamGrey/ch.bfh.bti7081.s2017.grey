package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
public class Habit {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;
    @OneToMany(mappedBy = "habit")
    private List<PatientHabitAssociation> patients;

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

    public List<PatientHabitAssociation> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientHabitAssociation> patients) {
        this.patients = patients;
    }
}
