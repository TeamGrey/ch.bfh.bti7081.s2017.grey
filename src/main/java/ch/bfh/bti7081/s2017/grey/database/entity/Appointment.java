package ch.bfh.bti7081.s2017.grey.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Quentin
 */
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue
    private long id;
    private Timestamp date;
    private Timestamp endDate;
    private String title;
    private String description;
    @ManyToOne
    private Staff staff;
    @ManyToOne
    private Patient patient;
    private Timestamp finished;
    private String protocol;
    private int delay;
    private Timestamp created;
    private Timestamp changed;
    private AppointmentStatus status = AppointmentStatus.NONE;
    @OneToMany(mappedBy = "appointment")
    private List<Task> tasks;

    public Appointment() {
    }

    public Appointment(Timestamp date, Timestamp endDate, String title, String description, Staff staff, Patient patient, Timestamp created, Timestamp changed) {
        this.date = date;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.staff = staff;
        this.patient = patient;
        this.created = created;
        this.changed = changed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date.toLocalDateTime();
    }

    public void setDate(LocalDateTime date) {
        this.date = Timestamp.valueOf(date);
    }

    public LocalDateTime getEndDate() { return endDate.toLocalDateTime();}

    public void setEndDate(LocalDateTime end) {
        this.endDate = Timestamp.valueOf(end);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Timestamp getFinished() {
        return finished;
    }

    public void setFinished(Timestamp finished) {
        this.finished = finished;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        if (status != null && status != this.status) {
            this.status = status;
        }
    }

    public void create() { setStatus(status.create(this)); }

    public void delay() {
        setStatus(status.delay(this));
    }

    public void cancel() {
        setStatus(status.cancel(this));
    }

    public void finish() {
        setStatus(status.finish(this));
    }


    public List<Task> getTasks() {
        tasks.sort((o1, o2) -> o1.getId() < o2.getId() ? -1 : 1);
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}