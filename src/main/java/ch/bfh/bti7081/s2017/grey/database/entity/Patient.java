package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue
    private int id;

}
