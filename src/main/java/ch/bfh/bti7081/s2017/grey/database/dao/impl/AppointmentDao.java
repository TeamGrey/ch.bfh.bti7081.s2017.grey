package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public class AppointmentDao extends GenericDaoImpl<Appointment> implements GenericDao<Appointment> {

    public List<Appointment> findAppointmentsForStaffAndDay(Staff staff, LocalDate date) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
        Root<Appointment> appointment = criteriaQuery.from(Appointment.class);
        LocalDateTime startOfDay = date.atStartOfDay();
        Timestamp start = Timestamp.valueOf(date.atStartOfDay());
        LocalDateTime endOfDay = startOfDay.with(LocalTime.MAX);
        Timestamp end = Timestamp.valueOf(endOfDay);
        criteriaQuery.select(appointment).where(criteriaBuilder.between(appointment.get("date"), start, end),
                criteriaBuilder.equal(appointment.get("staff"), staff));

        TypedQuery<Appointment> query = em.createQuery(criteriaQuery);
        List<Appointment> appointments =  query.getResultList();
        return appointments;
    }

    public List<Appointment> findAppointmentsForStaffAndDateRange(Staff staff, LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
        Root<Appointment> appointment = criteriaQuery.from(Appointment.class);
        criteriaQuery.select(appointment).where(criteriaBuilder.between(appointment.get("date"), Timestamp.valueOf(start), Timestamp.valueOf(end)),
                criteriaBuilder.equal(appointment.get("staff"), staff));

        TypedQuery<Appointment> query = em.createQuery(criteriaQuery);
        List<Appointment> appointments =  query.getResultList();
        return appointments;
    }
}