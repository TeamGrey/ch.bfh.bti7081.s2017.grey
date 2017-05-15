package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private Timestamp created;
    private Timestamp changed;
    @ManyToMany(targetEntity = Drug.class)
    List<Drug> drugs;
    @ManyToMany(targetEntity = Habit.class)
    List<Habit> habits;

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

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }
}
