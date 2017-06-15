package ch.bfh.bti7081.s2017.grey.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Quentin
 * @version 21.05.2017
 */
@Entity
@Table(name = "patient_habit")
@IdClass(PatientHabitAssociationId.class)
public class PatientHabitAssociation {

  @Id
  @Column(name = "patient_id")
  private long patientId;
  @Id
  @Column(name = "habit_id")
  private long habitId;
  @Column(name = "created")
  private Timestamp created;
  @Column(name = "changed")
  private Timestamp changed;
  @ManyToOne
  @JoinColumn(name = "patient_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Patient patient;
  @ManyToOne
  @JoinColumn(name = "habit_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Habit habit;

  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }

  public long getHabitId() {
    return habitId;
  }

  public void setHabitId(long habitId) {
    this.habitId = habitId;
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

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Habit getHabit() {
    return habit;
  }

  public void setHabit(Habit habit) {
    this.habit = habit;
  }
}
