package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.swing.table.TableStringConverter;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Drug {
    @Id
    long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;
    @ManyToMany(targetEntity = Patient.class)
    private List<Patient> patients;

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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
