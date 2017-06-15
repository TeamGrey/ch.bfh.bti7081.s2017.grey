package ch.bfh.bti7081.s2017.grey.database.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author Quentin
 */
@Entity
public class Role {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private Timestamp created;
  private Timestamp changed;

  public Role() {
  }

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
}
