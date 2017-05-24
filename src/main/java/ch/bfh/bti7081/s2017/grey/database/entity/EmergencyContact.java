package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Quentin
 */
@Entity
@Table(name = "emergencycontact")
public class EmergencyContact {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String lastname;
    private String phonenumber;
    @ManyToOne
    private Patient patient;
    private Timestamp created;
    private Timestamp changed;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
}
