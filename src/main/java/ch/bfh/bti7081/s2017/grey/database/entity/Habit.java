package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Habit {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;
    @ManyToMany(targetEntity = Patient.class)
    private List<Patient> patients;

}
