package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Author Quentin
 */
public class AppointmentDao {

    public List<Appointment> findAppointmentsForStaffAndDay(Staff staff, LocalDate date) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
        Root<Appointment> appointment = criteriaQuery.from(Appointment.class);
        LocalDateTime startOfDay = date.atStartOfDay();
        Timestamp start = Timestamp.valueOf(date.atStartOfDay());
        LocalDateTime endOfDay = startOfDay.with(LocalTime.MAX);
        Timestamp end = Timestamp.valueOf(endOfDay);
        criteriaQuery.select(appointment).where(criteriaBuilder.between(appointment.get("date"), start, end),
                criteriaBuilder.equal(appointment.get("staff"), staff));

        TypedQuery<Appointment> query = entityManager.createQuery(criteriaQuery);
        List<Appointment> appointments =  query.getResultList();
        return appointments;
    }

    public void createAppointment(LocalDateTime date, LocalDateTime end, String title, String description, Staff staff, Patient patient) {

        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Instant instant = Instant.now();
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setEndDate(end);
        appointment.setTitle(title);
        appointment.setDescription(description);
        appointment.setStaff(staff);
        appointment.setPatient(patient);
        appointment.setCreated(new Timestamp(instant.toEpochMilli()));
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.create();

        entitymanager.persist(appointment);
        entitymanager.getTransaction().commit();
    }

    public void delayAppointment(long id, LocalDateTime newDate, LocalDateTime newEnd) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setDate(newDate);
        appointment.setEndDate(newEnd);
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.delay();

        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void cancelAppointment(long id) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.cancel();

        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void finishAppointment(long id, LocalDateTime finished, int delay) {
        EntityManager entityManager = EntityManagerSingleton.getInstance();
        entityManager.getTransaction().begin();

        Instant instant = Instant.now();
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setChanged(new Timestamp(instant.toEpochMilli()));
        appointment.setFinished(Timestamp.valueOf(finished));
        appointment.setDelay(delay);
        appointment.finish();

        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
    }

    public void removeAppointment(long id) {
        EntityManager entitymanager = EntityManagerSingleton.getInstance();
        entitymanager.getTransaction().begin();

        Appointment appointment = entitymanager.find(Appointment.class, id);
        entitymanager.remove(appointment);
        entitymanager.getTransaction().commit();
    }
}
