package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gabor on 17/05/17.
 */
@Entity
@Table
public class AppointmentStatus {
    @Id
    @GeneratedValue
    private long id;
    private String name;

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
}
