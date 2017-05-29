package ch.bfh.bti7081.s2017.grey.database.dao;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Staff;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public interface AppointmentDao extends GenericDao<Appointment> {
    public List<Appointment> findAppointmentsForStaffAndDay (Staff staff, LocalDate date);
}
