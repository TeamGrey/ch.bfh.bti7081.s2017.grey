package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private Timestamp created;
    private Timestamp changed;
    @OneToMany(mappedBy = "patient")
    private List<PatientDrugAssociation> drugs = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    private List<PatientHabitAssociation> habits = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public void setChanged(Timestamp chnaged) {
        this.changed = chnaged;
    }

    public List<PatientDrugAssociation> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<PatientDrugAssociation> drugs) {
        this.drugs = drugs;
    }

    public List<PatientHabitAssociation> getHabits() {
        return habits;
    }

    public void setHabits(List<PatientHabitAssociation> habits) {
        this.habits = habits;
    }
}
