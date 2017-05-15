package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Timestamp created;
    private Timestamp changed;

}
